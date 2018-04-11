package com.edudigital.cloudy.amp.msg.service.service.msg.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MsgChannel;
import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MsgStatus;
import com.edudigital.cloudy.amp.msg.base.entity.MsgDTO;
import com.edudigital.cloudy.amp.msg.service.constant.MsgConstant;
import com.edudigital.cloudy.amp.msg.service.entity.po.MsgTemplatePO;
import com.edudigital.cloudy.amp.msg.service.mapper.MsgMapper;
import com.edudigital.cloudy.amp.msg.service.service.BaseService;
import com.edudigital.cloudy.amp.msg.service.service.msg.IMsgService;
import com.edudigital.cloudy.amp.msg.service.service.redis.IRedisClt;
import com.edudigital.cloudy.amp.util.base.util.CodeUtils;

import net.sf.json.JSONObject;

@Service
public class MsgServiceImpl extends BaseService implements IMsgService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1473627298793887104L;

	@Autowired
	private MsgConstant msgConstant;

	@Autowired
	private IRedisClt redisClt;

	@Autowired
	private MsgMapper msgMapper;

	@Override
	public MsgDTO sendMsg(MsgDTO msgDTO) {
		if (msgDTO == null) {
			logger.debug("短信参数为null");
			return null;
		}
		msgDTO.setMsgChannel(msgDTO.getMsgChannel() != null ? msgDTO.getMsgChannel() : MsgChannel.ALI);
		if (MsgChannel.ALI.toString().equals(msgDTO.getMsgChannel().toString())) {
			// 获取模版信息
			MsgTemplatePO msgTemplatePO = new MsgTemplatePO();
			msgTemplatePO.setChannel(msgDTO.getMsgChannel().toString());
			msgTemplatePO.setBizCode(msgDTO.getMsgMethod().toString());
			msgTemplatePO = msgMapper.getMsgTemplate(msgTemplatePO);
			if (null == msgTemplatePO || StringUtils.isBlank(msgTemplatePO.getSignName())
					|| StringUtils.isBlank(msgTemplatePO.getTemplateCode())) {
				return msgDTO;
			}
			sendMsgByAli(msgDTO, msgTemplatePO);
		}

		return msgDTO;
	}

	private MsgDTO sendMsgByAli(MsgDTO msgDTO, MsgTemplatePO msgTemplatePO) {
		//
		SendSmsResponse sendSmsResponse = new SendSmsResponse();
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化ascClient需要的几个参数
		final String product = msgConstant.ALI_PRODUCT;// 短信API产品名称（短信产品名固定，无需修改）
		final String domain = msgConstant.ALI_DOMAIN;// 短信API产品域名（接口地址固定，无需修改）
		// 替换成你的AK
		final String accessKeyId = msgConstant.ALI_ACCESS_KEY_ID;// 你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = msgConstant.ALI_ACCESS_KEY_SECRET;// 你的accessKeySecret，参考本文档步骤2
		// 初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile(msgConstant.ALI_REGION, accessKeyId, accessKeySecret);

		try {
			DefaultProfile.addEndpoint(msgConstant.ALI_REGION, msgConstant.ALI_REGION, product, domain);
		} catch (ClientException e1) {
			logger.error(e1.getMessage());
			return msgDTO;
		}
		// 往缓存中存入验证码
		JSONObject jsonObject = new JSONObject();
		String code = CodeUtils.getResource(1, 4);
		redisClt.save(msgDTO.getMsgID(), code, 300l);
		jsonObject.put("code", code);

		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(msgDTO.getPhones());
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(msgTemplatePO.getSignName());
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(msgTemplatePO.getTemplateCode());
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam(jsonObject.toString());
		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId(msgDTO.getMsgID());
		// 请求失败这里会抛ClientException异常
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return msgDTO;
		}
		if (null != sendSmsResponse && null != sendSmsResponse.getCode() && sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
			msgDTO.setMsgStatus(MsgStatus.SUCCESS);
		}
		return msgDTO;
	}

	@Override
	public boolean verifyMsg(String msgID, String code) {
		String str = null;
		try {
			str = (String) redisClt.get(msgID);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return code.equals(str);
	}

}

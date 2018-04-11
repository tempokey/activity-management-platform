package com.edudigital.cloudy.amp.order.service.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.order.base.entity.dto.FlowDTO;
import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.order.service.service.IFlowService;
import com.edudigital.cloudy.amp.order.service.service.IOrderService;
import com.edudigital.cloudy.amp.order.service.service.IRabbitMQSendService;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.RabbitMqMsgAO;
import com.edudigital.cloudy.dc.pay.constant.ALiPayConstants;
import com.edudigital.cloudy.dc.pay.constant.AdConstants;
import com.edudigital.cloudy.dc.pay.constant.TencentPayConstants;
import com.edudigital.cloudy.dc.pay.constant.enumeration.ChannelMethod;
import com.edudigital.cloudy.dc.pay.constant.enumeration.ProjectModule;
import com.edudigital.cloudy.dc.pay.constant.enumeration.TencentResultCode;
import com.edudigital.cloudy.dc.pay.core.PayClient;
import com.edudigital.cloudy.dc.pay.util.ali.ALiPayUtils;

import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/notify/")
public class NotifyController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IFlowService flowService;

	@Autowired
	private IRabbitMQSendService rabbitMQSendService;

	@RequestMapping(value = "pay/ali")
	public void aliNotify(HttpServletRequest req, HttpServletResponse res) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 获取所有返回参数
		Map<String, String[]> reqMapReadOnly = req.getParameterMap();
		Map<String, String[]> reqMap = new HashMap<String, String[]>();
		reqMap.putAll(reqMapReadOnly);
		Date date = new Date();

		Map<String, String> map = ALiPayUtils.sortMapAES(reqMap);
		String flowNo = map.get(ALiPayConstants.K_OUT_TRADE_NO);
		logger.info("流水:" + map.toString());

		boolean flag = PayClient.isSign(map, ChannelMethod.ALI);
		String gmt_payment = map.get("gmt_payment");
		String gmt_close = map.get("gmt_close");
		Date gmt_closeTime = new Date();
		Date gmt_paymenTime = new Date();
		try {
			gmt_paymenTime = sdf.parse(gmt_payment);
			gmt_closeTime = sdf.parse(gmt_close);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			logger.info("流水校验:" + flowNo);
			flag = PayClient.isSign(map, ChannelMethod.ALI);
			logger.info(flowNo + ":" + flag);
			if (flag) {

				FlowDTO flowDTO = new FlowDTO();
				flowDTO.setOutTradeNo(flowNo);
				List<FlowDTO> flowDTOs = flowService.listFlows(flowDTO);
				logger.info("校验对象是否存在:" + flowNo);
				if (flowDTOs != null && !flowDTOs.isEmpty()) {
					logger.info("存在对象:" + flowNo);
					flowDTO = flowDTOs.get(0);
					if (AdConstants.R_STATUS_SUCCESS == flowDTO.getResult()) {
						logger.info("该对象已处理过:" + flowNo);
						res.getWriter().print(ALiPayConstants.R_SUCCESS);
						return;
					} else {
						flowDTO.setStatus(AdConstants.O_STATUS_SUCCESS);
						flowDTO.setResult(AdConstants.R_STATUS_SUCCESS);
						flowDTO.setTimeExpire(date);
						flowService.update(flowDTO);

						OrderDTO orderDTO = new OrderDTO();
						orderDTO.setFlowNo(flowDTO.getOutTradeNo());
						orderDTO.setStatus(AdConstants.O_STATUS_SUCCESS);
						orderDTO.setResult(AdConstants.R_STATUS_SUCCESS);
						orderDTO.setUpdateTime(date);
						orderDTO.setCommitTime(gmt_closeTime);
						orderDTO.setPayTime(gmt_paymenTime);
						orderService.update(orderDTO);

						if (ProjectModule.EBK.toString().equals(flowDTO.getProject())) {
							List<OrderDTO> dtos = orderService.list(orderDTO);
							if (dtos != null && !dtos.isEmpty()) {
								orderDTO = dtos.get(0);

								JSONObject js = new JSONObject();
								js.put("orderDTO", orderDTO);

								RabbitMqMsgAO rabbitMqMsgAO2 = new RabbitMqMsgAO();
								RabbitMqMsgAO rabbitMqMsgAO = new RabbitMqMsgAO();

								rabbitMqMsgAO2.setMsg(js.toString());
								rabbitMqMsgAO2.setCode(orderDTO.getAdCode());// 爱丁号
								rabbitMqMsgAO2.setKey(orderDTO.getProductId());// 图书code
								rabbitMqMsgAO2.setTimestamp(new Date().getTime());
								rabbitMqMsgAO2.setStatus(new Integer(1).toString());
								rabbitMqMsgAO2.setProject(ProjectModule.EBK.toString());

								rabbitMqMsgAO.setCode(orderDTO.getAdCode());// 爱丁号
								rabbitMqMsgAO.setKey(orderDTO.getProductId());// 图书code
								rabbitMqMsgAO.setTimestamp(new Date().getTime());
								rabbitMqMsgAO.setMsg(orderDTO.getOrderNo());
								rabbitMqMsgAO.setStatus(new Integer(1).toString());
								rabbitMqMsgAO.setProject(ProjectModule.EBK.toString());
								rabbitMQSendService.sendQueue(rabbitMqMsgAO);
								rabbitMQSendService.sendGetUserQueue(rabbitMqMsgAO2);

							}
						}
					}
				}
				res.getWriter().print(ALiPayConstants.R_SUCCESS);
			} else {
				logger.info("支付异步通知接收到异常数据:" + flowNo);
			}
		} catch (Exception e) {
			logger.error(flowNo + "--" + e.getMessage());
		}
	}

	@RequestMapping(value = "pay/wx")
	public void wxNotify(HttpServletRequest req, HttpServletResponse res) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String xmlStr = PayClient.stream2Str(req);
		Map<String, String> map = PayClient.str2Map(xmlStr, ChannelMethod.TECENT);
		String flowNo = map.get(TencentPayConstants.K_OUT_TRADE_NO);
		logger.info("流水:" + map.toString());
		String returnCode = map.get(TencentPayConstants.R_RETURN_CODE);
		String time_end = map.get("time_end");
		Date end_time = new Date();
		try {
			end_time = sdf.parse(time_end);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			logger.info("流水校验:" + flowNo);
			boolean flag = PayClient.isXmlSign(xmlStr, ChannelMethod.TECENT);
			logger.info(flowNo + ":" + flag);
			if (flag && TencentResultCode.SUCCESS.toString().equals(returnCode)) {
				FlowDTO flowDTO = new FlowDTO();
				flowDTO.setOutTradeNo(flowNo);
				List<FlowDTO> flowDTOs = flowService.listFlows(flowDTO);
				logger.info("校验对象是否存在:" + flowNo);
				if (flowDTOs != null && !flowDTOs.isEmpty()) {
					logger.info("存在对象:" + flowNo);
					flowDTO = flowDTOs.get(0);
					if (AdConstants.R_STATUS_SUCCESS == flowDTO.getResult()) {
						logger.info("该对象已处理过:" + flowNo);
						res.getWriter().print(TencentPayConstants.R_SUCCESS);
						return;
					} else {
						Date date = new Date();
						flowDTO.setStatus(AdConstants.O_STATUS_SUCCESS);
						flowDTO.setResult(AdConstants.R_STATUS_SUCCESS);
						flowDTO.setTimeExpire(date);
						flowService.update(flowDTO);

						OrderDTO orderDTO = new OrderDTO();
						orderDTO.setFlowNo(flowDTO.getOutTradeNo());
						orderDTO.setStatus(AdConstants.O_STATUS_SUCCESS);
						orderDTO.setResult(AdConstants.R_STATUS_SUCCESS);
						orderDTO.setCommitTime(end_time);
						orderDTO.setUpdateTime(date);
						orderDTO.setPayTime(date);
						orderService.update(orderDTO);

						if (ProjectModule.EBK.toString().equals(flowDTO.getProject())) {
							List<OrderDTO> dtos = orderService.list(orderDTO);
							if (dtos != null && !dtos.isEmpty()) {
								orderDTO = dtos.get(0);

								JSONObject js = new JSONObject();
								js.put("orderDTO", orderDTO);

								RabbitMqMsgAO rabbitMqMsgAO2 = new RabbitMqMsgAO();
								RabbitMqMsgAO rabbitMqMsgAO = new RabbitMqMsgAO();

								rabbitMqMsgAO2.setMsg(js.toString());
								rabbitMqMsgAO2.setCode(orderDTO.getAdCode());// 爱丁号
								rabbitMqMsgAO2.setKey(orderDTO.getProductId());// 图书code
								rabbitMqMsgAO2.setTimestamp(new Date().getTime());
								rabbitMqMsgAO2.setStatus(new Integer(1).toString());
								rabbitMqMsgAO2.setProject(ProjectModule.EBK.toString());
								
								String s = orderDTO.getUserId() + "";

								rabbitMqMsgAO.setCode(s);// 用户号
								rabbitMqMsgAO.setKey(orderDTO.getProductId());// 图书code
								rabbitMqMsgAO.setTimestamp(new Date().getTime());
								rabbitMqMsgAO.setMsg(orderDTO.getOrderNo());
								rabbitMqMsgAO.setStatus(new Integer(1).toString());
								rabbitMqMsgAO.setProject(ProjectModule.EBK.toString());
								rabbitMQSendService.sendQueue(rabbitMqMsgAO);
								rabbitMQSendService.sendGetUserQueue(rabbitMqMsgAO2);
//								rabbitMQSendService.sendXxQueue(rabbitMqMsgAO);
							}
						}
					}
				}
				res.getWriter().print(TencentPayConstants.R_SUCCESS);
			} else {
				logger.info("支付异步通知接收到异常数据:" + flowNo);
			}
		} catch (Exception e) {
			logger.error(flowNo + "--" + e.getMessage());
		}
	}
}

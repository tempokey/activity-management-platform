package com.edudigital.cloudy.amp.order.service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.order.base.constant.enumeration.PayMethod;
import com.edudigital.cloudy.amp.order.base.constant.enumeration.ResKey;
import com.edudigital.cloudy.amp.order.base.entity.dto.FlowDTO;
import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.order.service.dao.IFlowDAO;
import com.edudigital.cloudy.amp.order.service.dao.IOrderDAO;
import com.edudigital.cloudy.amp.order.service.service.BaseYamlValues;
import com.edudigital.cloudy.amp.order.service.service.IOrderService;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PreOrderDTO;
import com.edudigital.cloudy.dc.pay.constant.ALiPayConstants;
import com.edudigital.cloudy.dc.pay.constant.TencentPayConstants;
import com.edudigital.cloudy.dc.pay.constant.enumeration.ChannelMethod;
import com.edudigital.cloudy.dc.pay.constant.enumeration.InoutcashMethod;
import com.edudigital.cloudy.dc.pay.constant.enumeration.ProjectModule;
import com.edudigital.cloudy.dc.pay.constant.enumeration.ResCode;
import com.edudigital.cloudy.dc.pay.core.PayClient;
import com.edudigital.cloudy.dc.pay.core.PayRes;
import com.edudigital.cloudy.dc.pay.util.OrderNest;

import net.sf.json.JSONObject;

@Service
public class OrderServiceImpl extends BaseYamlValues implements IOrderService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IOrderDAO orderDAO;

	@Autowired
	private IFlowDAO flowDAO;

	@Override
	public int add(OrderDTO orderBO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		OrderDTO orderDTO = new OrderDTO();

		// 流水
		FlowDTO flowDTO = new FlowDTO();
		flowDTO.setAppid(ALiPayConstants.B_APP_ID);
		flowDTO.setChannel(ChannelMethod.ALI.toString());
		flowDTO.setProject(orderBO.getProject());
		flowDTO.setSignType(ALiPayConstants.B_SIGN_TYPE);
		flowDTO.setBody(orderBO.getContext());
		// 流水号作为订单号
		flowDTO.setOutTradeNo(orderBO.getFlowNo());
		flowDTO.setTotalFee(orderBO.getPrice());
		flowDTO.setNotifyUrl(notify_url);
		flowDTO.setProductId(orderBO.getProductId());
		flowDTO.setType(InoutcashMethod.DEPOSIT.toString());
		flowDTO.setTimeStart(orderBO.getCreateTime());
		flowDAO.add(flowDTO);

		BeanUtils.copyProperties(orderBO, orderDTO);

		return orderDAO.add(orderDTO);
	}

	@Override
	public int update(OrderDTO orderBO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		OrderDTO orderDTO = new OrderDTO();

		BeanUtils.copyProperties(orderBO, orderDTO);

		return orderDAO.update(orderDTO);
	}

	@Override
	public List<OrderDTO> list(OrderDTO orderBO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<OrderDTO> boList = new ArrayList<>();

		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderBO, orderDTO);

		List<OrderDTO> dtoList = orderDAO.list(orderDTO);

		if (dtoList != null && !dtoList.isEmpty()) {
			for (OrderDTO orderDTO2 : dtoList) {
				OrderDTO orderBO2 = new OrderDTO();
				BeanUtils.copyProperties(orderDTO2, orderBO2);
				boList.add(orderBO2);
			}
		}

		return boList;
	}

	/****
	 * 支付操作
	 */
	@Override
	public JSONObject preOrder(PreOrderDTO preOrderDTO) {
		// 传递参数
		Map<String, String> map = new HashMap<String, String>();
		// 返回对象
		JSONObject jo = new JSONObject();
		PayRes myRes = new PayRes();

		if (PayMethod.ALI.getType().equals(preOrderDTO.getPayStyle())) {
			// 生成订单
			String orderNo = OrderNest.creAliOrder();
			// 流水号
			String flowNo = OrderNest.creAliFlow();

			logger.info(orderNo);
			logger.info(flowNo);

			// 流水号，交易只挂流水
			map.put(ALiPayConstants.K_OUT_TRADE_NO, flowNo);
			map.put(ALiPayConstants.K_TOTAL_AMOUNT, preOrderDTO.getTotalAmount());
			map.put(ALiPayConstants.K_SUBJECT, preOrderDTO.getSubject());
			map.put(ALiPayConstants.K_TIMEOUT_EXPRESS, ALiPayConstants.V_TIMEOUT_EXPRESS);
			map.put(ALiPayConstants.K_NOTIFY_URL, ali_notify_url);

			// 支付宝预支付
			myRes = PayClient.preOrder(map, ChannelMethod.ALI);
			// TencentPay.encode2(myRes.getData().toString(), 1000, 1000, "F://zxing.png");

			if (ResCode.SUCCESS.toString().equals(myRes.getCode())) {
				// 保存订单
				jo = getSucessMethod(preOrderDTO, orderNo, flowNo);
			}
			jo.put(ResKey.COMMON_RETURN.getType(), myRes);

		} else if (PayMethod.WX.getType().equals(preOrderDTO.getPayStyle())) {
			// 生成订单
			String orderNo = OrderNest.creAliOrder();
			// 流水号
			String flowNo = OrderNest.creAliFlow();

			logger.info(orderNo);
			logger.info(flowNo);

			// 流水号，交易只挂流水
			// 商品描述
			map.put(TencentPayConstants.K_BODY, preOrderDTO.getSubject());
			// 商户订单号
			map.put(TencentPayConstants.K_OUT_TRADE_NO, flowNo);
			// 标价金额
			map.put(TencentPayConstants.K_TOTAL_FEE,
					"" + new Double((new Double(preOrderDTO.getTotalAmount()) * 100)).intValue());
			// 终端IP
			map.put(TencentPayConstants.K_SPBILL_CREATE_IP, preOrderDTO.getK_Spbill_Create_Ip());
			// 通知地址
			map.put(TencentPayConstants.K_NOTIFY_URL, wx_notify_url);
			// 交易类型
			map.put(TencentPayConstants.K_TRADE_TYPE, TencentPayConstants.V_TRADE_TYPE_NATIVE);
			// 商品ID
			map.put(TencentPayConstants.K_PRODCUT_ID, preOrderDTO.getProductId());

			// 创建微信预支付订单
			myRes = PayClient.preOrder(map, ChannelMethod.TECENT);
			// 后台本地生成二维码
			// TencentPay.encode2(myRes.getData().toString(), 1000, 1000, "F://zxing.png");
			if (ResCode.SUCCESS.toString().equals(myRes.getCode())) {
				// 保存订单
				jo = getSucessMethod(preOrderDTO, orderNo, flowNo);
			}
			jo.put(ResKey.COMMON_RETURN.getType(), myRes);
		}
		return jo;
	}

	/****
	 * 返回成功保存订单信息
	 * 
	 * @author yangs
	 * @param preOrderDTO
	 * @param orderNo
	 * @param flowNo
	 * @return
	 */
	private JSONObject getSucessMethod(PreOrderDTO preOrderDTO, String orderNo, String flowNo) {

		// 返回对象
		JSONObject jo = new JSONObject();
		Date date = new Date();
		OrderDTO orderDTO = new OrderDTO();

		orderDTO.setProject(ProjectModule.EBK.toString());
		// 用户显示订单号
		orderDTO.setOrderNo(orderNo);
		orderDTO.setMethod(ChannelMethod.TECENT.toString());

		double price = 0;
		try {
			price = new Double(preOrderDTO.getTotalAmount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		orderDTO.setPrice(price);
		orderDTO.setContext(preOrderDTO.getSubject());
		orderDTO.setProductId(preOrderDTO.getProductId());
		orderDTO.setUserId(preOrderDTO.getUserId());
		orderDTO.setAdCode(preOrderDTO.getAdCode());
		// 关联内部流水
		orderDTO.setFlowNo(flowNo);
		orderDTO.setCreateTime(date);
		orderDTO.setUpdateTime(date);
		add(orderDTO);
		jo.put(ResKey.ORDER_NO.getType(), orderNo);

		return jo;
	}

	@Override
	public List<OrderDTO> getOrderBy(OrderDTO orderDTO) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<OrderDTO> dtoList = orderDAO.getOrderBy(orderDTO);

		return dtoList;
	}

}

package com.edudigital.cloudy.amp.order.service.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.order.service.service.IOrderService;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PreOrderDTO;
import com.edudigital.cloudy.amp.util.base.util.res.ResUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Api(value = "Order API Docs")
@RestController
@RequestMapping(value = "/order/")
public class OrderController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IOrderService orderService;

	@ApiOperation(value = "创建预支付订单", notes = "移动端移动支付接口")
	@ApiImplicitParam(name = "preOrderDTO", value = "预支付订单信息", dataType = "PreOrderDTO")
	@RequestMapping(value = "preOrder", method = { RequestMethod.POST })
	public JSONObject precreate(HttpServletRequest req, HttpServletResponse res, @RequestBody PreOrderDTO preOrderDTO) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		JSONObject jsonObject = orderService.preOrder(preOrderDTO);

		return jsonObject;
	}

	@ApiOperation(value = "查询订单", notes = "移动端移动支付接口")
	@ApiImplicitParam(name = "orderDTO", value = "订单信息", required = true, dataType = "OrderDTO")
	@RequestMapping(value = "Check", method = { RequestMethod.GET })
	public void check(HttpServletRequest req, HttpServletResponse res, OrderDTO orderDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		JSONArray jsonArray = new JSONArray();

		List<OrderDTO> boList = orderService.list(orderDTO);

		if (boList != null && !boList.isEmpty()) {
			orderDTO = boList.get(0);
		}

		jsonArray.add(orderDTO);
		ResUtils.toJsonPatched(res, jsonArray);
	}

	@RequestMapping(value = "listOrderBy", method = RequestMethod.GET)
	public List<OrderDTO> listOrderBy(HttpServletRequest req, HttpServletResponse res, OrderDTO orderDTO) {

		this.logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<OrderDTO> dtoList = orderService.getOrderBy(orderDTO);

		return dtoList;
	}
}

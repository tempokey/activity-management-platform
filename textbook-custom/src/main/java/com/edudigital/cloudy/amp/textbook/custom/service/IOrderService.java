package com.edudigital.cloudy.amp.textbook.custom.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PreOrderDTO;

import net.sf.json.JSONObject;

@FeignClient(name = "order-service")
public interface IOrderService {

	@RequestMapping(value = "/order-service/order/preOrder", method = { RequestMethod.POST })
	public JSONObject precreate(@RequestBody PreOrderDTO preOrderDTO);

	@RequestMapping(value = "/order-service/order/Check", method = { RequestMethod.GET })
	public void check(@RequestParam("orderDTO") OrderDTO orderDTO);

	@RequestMapping(value = "/order-service/order/listOrderBy", method = { RequestMethod.GET })
	public List<OrderDTO> listOrderBy(@RequestParam Map<String, Object> map);

}

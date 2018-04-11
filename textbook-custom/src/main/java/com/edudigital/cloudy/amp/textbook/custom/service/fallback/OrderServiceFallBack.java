package com.edudigital.cloudy.amp.textbook.custom.service.fallback;

import java.util.List;
import java.util.Map;

import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PreOrderDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.IOrderService;

import net.sf.json.JSONObject;

public class OrderServiceFallBack implements IOrderService{

	@Override
	public JSONObject precreate(PreOrderDTO preOrderDTO) {
		
		return null;
	}

	@Override
	public void check(OrderDTO orderDTO) {
		
		
	}

	@Override
	public List<OrderDTO> listOrderBy(Map<String, Object> map) {
		
		return null;
	}

}

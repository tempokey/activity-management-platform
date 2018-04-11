package com.edudigital.cloudy.amp.order.service.service;

import java.util.List;

import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PreOrderDTO;

import net.sf.json.JSONObject;

public interface IOrderService {
	/**
	 * 
	 * @param orderDTO
	 * @return
	 */
	public int add(OrderDTO orderDTO);

	/**
	 * 
	 * @param orderDTO
	 * @return
	 */
	public int update(OrderDTO orderDTO);

	/**
	 * 
	 * @param orderDTO
	 * @return
	 */
	public List<OrderDTO> list(OrderDTO orderDTO);

	/****
	 * 
	 * @param preOrderDTO
	 */
	public JSONObject preOrder(PreOrderDTO preOrderDTO);

	/**
	 * 
	 * @param orderDTO
	 * @return
	 */
	public List<OrderDTO> getOrderBy(OrderDTO orderDTO);
}

package com.edudigital.cloudy.amp.order.service.dao;

import java.util.List;

import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;

public interface IOrderDAO {
	/**
	 * 
	 * @param orderDO
	 * @return
	 */
	int add(OrderDTO orderDTO);

	/**
	 * 
	 * @param orderDTO
	 * @return
	 */
	int update(OrderDTO orderDTO);

	/**
	 * 
	 * @param orderDTO
	 * @return
	 */
	List<OrderDTO> list(OrderDTO orderDTO);

	/****
	 * 
	 * @param orderDTO
	 * @return
	 */
	List<OrderDTO> getOrderBy(OrderDTO orderDTO);
}

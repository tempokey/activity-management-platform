package com.edudigital.cloudy.amp.order.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.order.service.dao.IOrderDAO;
import com.edudigital.cloudy.amp.order.service.entity.po.OrderPO;
import com.edudigital.cloudy.amp.order.service.mapper.OrderMapper;

@Component
public class OrderDAO implements IOrderDAO {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderMapper orderMapper;

	@Transactional(readOnly = false)
	@Override
	public int add(OrderDTO orderDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		OrderPO orderDO = new OrderPO();
		BeanUtils.copyProperties(orderDTO, orderDO);

		return orderMapper.add(orderDO);
	}

	@Transactional(readOnly = false)
	@Override
	public int update(OrderDTO orderDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		OrderPO orderDO = new OrderPO();
		BeanUtils.copyProperties(orderDTO, orderDO);

		return orderMapper.update(orderDO);
	}

	@Override
	public List<OrderDTO> list(OrderDTO orderDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<OrderDTO> dtoList = new ArrayList<>();

		OrderPO orderDO = new OrderPO();
		BeanUtils.copyProperties(orderDTO, orderDO);

		List<OrderPO> doList = orderMapper.list(orderDO);

		if (doList != null && !doList.isEmpty()) {
			for (OrderPO orderDO2 : doList) {
				OrderDTO orderDTO2 = new OrderDTO();
				BeanUtils.copyProperties(orderDO2, orderDTO2);
				dtoList.add(orderDTO2);
			}
		}

		return dtoList;
	}

	/***
	 * 查询WEB订单列表
	 */
	@Override
	public List<OrderDTO> getOrderBy(OrderDTO orderDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<OrderDTO> dtoList = new ArrayList<>();

		OrderPO orderPO = new OrderPO();
		BeanUtils.copyProperties(orderDTO, orderPO);

		List<OrderPO> doList = orderMapper.getOrderBy(orderPO);

		if (doList != null && !doList.isEmpty()) {

			for (OrderPO orderDO2 : doList) {

				OrderDTO orderDTO2 = new OrderDTO();
				BeanUtils.copyProperties(orderDO2, orderDTO2);
				dtoList.add(orderDTO2);
			}
		}
		return dtoList;
	}

}

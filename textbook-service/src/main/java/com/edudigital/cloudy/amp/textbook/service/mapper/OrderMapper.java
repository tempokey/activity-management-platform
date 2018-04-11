package com.edudigital.cloudy.amp.textbook.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.textbook.service.entity.po.OrderPO;

@Component
public interface OrderMapper {
	/**
	 * 
	 * @param orderDO
	 * @return
	 */
	int add(@Param("o") OrderPO orderDO);

	/**
	 * 
	 * @param orderDO
	 * @return
	 */
	int update(@Param("o") OrderPO orderDO);

	/**
	 * 
	 * @param orderDO
	 * @return
	 */
	List<OrderPO> list(@Param("o") OrderPO orderDO);
}

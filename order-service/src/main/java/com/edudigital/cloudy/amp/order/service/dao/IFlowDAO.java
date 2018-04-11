package com.edudigital.cloudy.amp.order.service.dao;

import java.util.List;

import com.edudigital.cloudy.amp.order.base.entity.dto.FlowDTO;

public interface IFlowDAO {
	/**
	 * 
	 * @param flowDTO
	 * @return
	 */
	int add(FlowDTO flowDTO);

	/**
	 * 
	 * @param flowDTO
	 * @return
	 */
	int update(FlowDTO flowDTO);

	/**
	 * 
	 * @param flowDTO
	 * @return
	 */
	List<FlowDTO> listFlows(FlowDTO flowDTO);
}

package com.edudigital.cloudy.amp.order.service.service;

import java.util.List;

import com.edudigital.cloudy.amp.order.base.entity.dto.FlowDTO;

public interface IFlowService {
	/**
	 * 
	 * @param flowBO
	 * @return
	 */
	public int add(FlowDTO flowBO);

	/**
	 * 
	 * @param flowBO
	 * @return
	 */
	public int update(FlowDTO flowBO);

	/**
	 * 
	 * @param flowBO
	 * @return
	 */
	public List<FlowDTO> listFlows(FlowDTO flowBO);
}

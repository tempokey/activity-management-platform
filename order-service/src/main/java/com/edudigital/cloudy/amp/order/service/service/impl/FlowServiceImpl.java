package com.edudigital.cloudy.amp.order.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.order.base.entity.dto.FlowDTO;
import com.edudigital.cloudy.amp.order.service.dao.IFlowDAO;
import com.edudigital.cloudy.amp.order.service.service.IFlowService;

@Service
public class FlowServiceImpl implements IFlowService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IFlowDAO flowDAO;

	@Override
	public int add(FlowDTO flowBO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		FlowDTO flowDTO = new FlowDTO();

		BeanUtils.copyProperties(flowBO, flowDTO);

		return flowDAO.add(flowDTO);
	}

	@Override
	public List<FlowDTO> listFlows(FlowDTO flowBO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<FlowDTO> boList = new ArrayList<>();
		FlowDTO flowDTO = new FlowDTO();

		BeanUtils.copyProperties(flowBO, flowDTO);

		List<FlowDTO> dtoList = flowDAO.listFlows(flowDTO);

		if (dtoList != null && !dtoList.isEmpty()) {
			for (FlowDTO flowDTO2 : dtoList) {
				FlowDTO flowBO2 = new FlowDTO();
				BeanUtils.copyProperties(flowDTO2, flowBO2);
				boList.add(flowBO2);
			}
		}

		return boList;
	}

	@Override
	public int update(FlowDTO flowBO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		FlowDTO flowDTO = new FlowDTO();

		BeanUtils.copyProperties(flowBO, flowDTO);

		return flowDAO.update(flowDTO);
	}

}

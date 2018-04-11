package com.edudigital.cloudy.amp.order.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.edudigital.cloudy.amp.order.base.entity.dto.FlowDTO;
import com.edudigital.cloudy.amp.order.service.dao.IFlowDAO;
import com.edudigital.cloudy.amp.order.service.entity.po.FlowPO;
import com.edudigital.cloudy.amp.order.service.mapper.FlowMapper;

@Component
public class FlowDAO implements IFlowDAO {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FlowMapper flowMapper;

	@Transactional(readOnly = false)
	@Override
	public int add(FlowDTO flowDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		FlowPO flowDO = new FlowPO();
		BeanUtils.copyProperties(flowDTO, flowDO);

		return flowMapper.add(flowDO);
	}

	@Override
	public List<FlowDTO> listFlows(FlowDTO flowDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<FlowDTO> dtoList = new ArrayList<>();

		FlowPO flowDO = new FlowPO();
		BeanUtils.copyProperties(flowDTO, flowDO);

		List<FlowPO> doList = flowMapper.listFlows(flowDO);

		if (doList != null && !doList.isEmpty()) {
			for (FlowPO flowDO2 : doList) {
				FlowDTO flowDTO2 = new FlowDTO();
				BeanUtils.copyProperties(flowDO2, flowDTO2);
				dtoList.add(flowDTO2);
			}
		}

		return dtoList;
	}

	@Transactional(readOnly = false)
	@Override
	public int update(FlowDTO flowDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		FlowPO flowDO = new FlowPO();
		BeanUtils.copyProperties(flowDTO, flowDO);

		return flowMapper.update(flowDO);
	}

}

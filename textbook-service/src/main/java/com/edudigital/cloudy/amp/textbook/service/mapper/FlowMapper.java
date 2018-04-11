package com.edudigital.cloudy.amp.textbook.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.textbook.service.entity.po.FlowPO;

@Component
public interface FlowMapper {
	/**
	 * 
	 * @param flowDO
	 * @return
	 */
	int add(@Param("f") FlowPO flowDO);

	/**
	 * 
	 * @param flowDO
	 * @return
	 */
	int update(@Param("f") FlowPO flowDO);

	/**
	 * 
	 * @param flowDO
	 * @return
	 */
	List<FlowPO> listFlows(@Param("f") FlowPO flowDO);
}

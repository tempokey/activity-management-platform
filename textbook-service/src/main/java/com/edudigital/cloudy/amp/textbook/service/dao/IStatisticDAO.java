package com.edudigital.cloudy.amp.textbook.service.dao;

import java.util.List;

import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.DataPointDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.service.entity.ao.DataSeriesAO;
import com.edudigital.cloudy.amp.textbook.service.entity.ao.TimeAO;

public interface IStatisticDAO {
	/**
	 * 
	 * @param ebkDTO
	 * @return
	 */
	// 返回柱状图所需的数据集合
	public List<DataPointDTO> listEbkColumnChart(EbkDTO ebkDTO);

	public List<OrderDTO> listEbkSales();

	/**
	 * 条形图所需要的数据
	 * 
	 * @return
	 */
	public List<DataSeriesAO> getSta(TimeAO time);

}

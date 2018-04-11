package com.edudigital.cloudy.amp.textbook.service.service;

import java.util.List;

import com.edudigital.cloudy.amp.textbook.service.entity.ao.DataSeriesAO;
import com.edudigital.cloudy.amp.textbook.service.entity.ao.TimeAO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.DataPointBO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.EbkBO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.OrderBO;

public interface IStatisticService {
	/**
	 * 
	 * @param ebkBO
	 * @return
	 */
	public List<DataPointBO> listEbkColumnChart(EbkBO ebkBO);

	/**
	 * 
	 * @return
	 */
	public List<OrderBO> listEbkSales();

	/**
	 * 条形图数据集合
	 * 
	 * @return
	 */
	public List<DataSeriesAO> getSta(TimeAO time);
}

package com.edudigital.cloudy.amp.textbook.service.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.textbook.service.entity.ao.DataSeriesAO;
import com.edudigital.cloudy.amp.textbook.service.entity.ao.TimeAO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.DataPointPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.EbkPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.OrderPO;

/**
 * 
 * @author Administrator
 * 
 */
@Component
public interface StatisticMapper {
	/**
	 * 
	 * @param ebkPO
	 * @return
	 */
	// 返回柱状图所需的数据集合
	public List<DataPointPO> listEbkColumnChart(EbkPO ebkPO);

	/**
	 * 
	 * @return
	 */
	public List<OrderPO> listEbkSales();

	/**
	 * 条形图集合
	 * 
	 * @return
	 */
	public List<DataSeriesAO> getSta(TimeAO time);
}

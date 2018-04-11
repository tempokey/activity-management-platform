package com.edudigital.cloudy.amp.textbook.service.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.DataPointDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.service.dao.IStatisticDAO;
import com.edudigital.cloudy.amp.textbook.service.entity.ao.DataSeriesAO;
import com.edudigital.cloudy.amp.textbook.service.entity.ao.TimeAO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.DataPointPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.EbkPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.OrderPO;
import com.edudigital.cloudy.amp.textbook.service.mapper.StatisticMapper;
import com.edudigital.cloudy.amp.textbook.service.util.EntityUtils;

@Component
public class StatisticDAO implements IStatisticDAO {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StatisticMapper statisticMapper;

	/*******
	 * 统计条形数据
	 */
	@Override
	public List<DataSeriesAO> getSta(TimeAO time) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// 根据时间条件查询图书数据列表
		List<DataSeriesAO> aolist = statisticMapper.getSta(time);
		return aolist;
	}

	@Override
	public List<DataPointDTO> listEbkColumnChart(EbkDTO ebkDTO) {
		//
		List<DataPointDTO> chartList = null;
		//
		EbkPO ebkPO = new EbkPO();
		try {
			BeanUtils.copyProperties(ebkDTO, ebkPO);
			//
			List<DataPointPO> listEbkColumnChart = statisticMapper.listEbkColumnChart(ebkPO);
			//
			chartList = EntityUtils.exchange(listEbkColumnChart, DataPointDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chartList;
	}

	@Override
	public List<OrderDTO> listEbkSales() {
		List<OrderDTO> salesList = null;
		List<OrderPO> listEbkSales = statisticMapper.listEbkSales();
		try {
			salesList = EntityUtils.exchange(listEbkSales, OrderDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salesList;
	}

}

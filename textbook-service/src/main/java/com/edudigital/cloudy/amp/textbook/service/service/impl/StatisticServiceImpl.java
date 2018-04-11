package com.edudigital.cloudy.amp.textbook.service.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.DataPointDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.service.dao.impl.StatisticDAO;
import com.edudigital.cloudy.amp.textbook.service.entity.ao.DataSeriesAO;
import com.edudigital.cloudy.amp.textbook.service.entity.ao.TimeAO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.DataPointBO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.EbkBO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.OrderBO;
import com.edudigital.cloudy.amp.textbook.service.service.IStatisticService;
import com.edudigital.cloudy.amp.textbook.service.util.EntityUtils;

@Service
public class StatisticServiceImpl implements IStatisticService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StatisticDAO statisticDAO;

	/****
	 * 统计条形的数据
	 */
	@Override
	public List<DataSeriesAO> getSta(TimeAO time) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// 根据时间条件查询条形图所需要的数据
		List<DataSeriesAO> aolist = statisticDAO.getSta(time);
		return aolist;
	}

	@Override
	public List<DataPointBO> listEbkColumnChart(EbkBO ebkBO) {
		List<DataPointBO> chartList = null;
		EbkDTO ebkDTO = new EbkDTO();
		try {
			BeanUtils.copyProperties(ebkBO, ebkDTO);
			List<DataPointDTO> listEbkColumnChart = statisticDAO.listEbkColumnChart(ebkDTO);
			chartList = EntityUtils.exchange(listEbkColumnChart, DataPointBO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chartList;
	}

	@Override
	public List<OrderBO> listEbkSales() {
		List<OrderBO> salesList = null;
		List<OrderDTO> listEbkSales = statisticDAO.listEbkSales();
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

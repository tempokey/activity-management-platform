package com.edudigital.cloudy.amp.camp.custom.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.camp.base.entity.MatchItemDTO;
import com.edudigital.cloudy.amp.camp.custom.dao.IMatchItemDAO;
import com.edudigital.cloudy.amp.camp.custom.entity.po.MatchItemPO;
import com.edudigital.cloudy.amp.camp.custom.mapper.MatchItemMapper;
import com.edudigital.cloudy.amp.camp.custom.util.EntityUtils;

@Service
public class MatchItemDAO implements IMatchItemDAO {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MatchItemMapper matchItemMapper;

	@Override
	public List<MatchItemDTO> listMatchItems() {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<MatchItemDTO> dtos = new ArrayList<>();

		List<MatchItemPO> pos = matchItemMapper.listMatchItems();

		try {
			dtos = EntityUtils.exchange(pos, MatchItemDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

}

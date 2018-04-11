package com.edudigital.cloudy.amp.camp.custom.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.camp.base.entity.MatchItemDTO;
import com.edudigital.cloudy.amp.camp.custom.dao.IMatchItemDAO;
import com.edudigital.cloudy.amp.camp.custom.service.IMatchItemService;

@Service
public class MatchItemServiceImpl implements IMatchItemService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IMatchItemDAO matchItemDAO;

	@Override
	public List<MatchItemDTO> listMatchItems() {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<MatchItemDTO> dtos = matchItemDAO.listMatchItems();

		return dtos;
	}

}

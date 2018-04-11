package com.edudigital.cloudy.amp.camp.custom.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.camp.base.entity.MatchScoreDTO;
import com.edudigital.cloudy.amp.camp.custom.dao.IMatchScoreDAO;
import com.edudigital.cloudy.amp.camp.custom.service.IMatchScoreService;

@Service
public class MatchScoreServiceImpl implements IMatchScoreService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IMatchScoreDAO matchScoreDAO;

	@Override
	public List<MatchScoreDTO> listMatchScore(int matchId, int itemId, int page, int size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<MatchScoreDTO> dtos = matchScoreDAO.listMatchScore(matchId, itemId, page, size);

		return dtos;
	}

	@Override
	public int countMatchScore(int matchId, int itemId) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return matchScoreDAO.countMatchScore( matchId, itemId);
	}

}

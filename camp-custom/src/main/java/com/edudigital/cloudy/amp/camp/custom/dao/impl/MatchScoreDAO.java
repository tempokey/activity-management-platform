package com.edudigital.cloudy.amp.camp.custom.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.camp.base.entity.MatchScoreDTO;
import com.edudigital.cloudy.amp.camp.custom.dao.IMatchScoreDAO;
import com.edudigital.cloudy.amp.camp.custom.entity.po.MatchScorePO;
import com.edudigital.cloudy.amp.camp.custom.mapper.MatchScoreMapper;
import com.edudigital.cloudy.amp.camp.custom.util.EntityUtils;

@Service
public class MatchScoreDAO implements IMatchScoreDAO {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MatchScoreMapper matchScoreMapper;

	@Override
	public List<MatchScoreDTO> listMatchScore(int matchId, int itemId, int page, int size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<MatchScoreDTO> dtos = new ArrayList<>();

		List<MatchScorePO> pos = matchScoreMapper.listMatchScore(matchId, itemId, page, size);

		try {
			dtos = EntityUtils.exchange(pos, MatchScoreDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

	@Override
	public int countMatchScore(int matchId, int itemId) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return matchScoreMapper.countMatchScore(matchId, itemId);
	}

}

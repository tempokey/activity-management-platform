package com.edudigital.cloudy.amp.textbook.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxInfoDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxScoreDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PageGameWjxDTO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.GameInfoPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.GamePO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.WjxScorePO;
import com.edudigital.cloudy.amp.textbook.service.mapper.GameWjxMapper;
import com.edudigital.cloudy.amp.textbook.service.service.IGameWjxService;
import com.edudigital.cloudy.amp.textbook.service.util.EntityUtils;

@Service
public class GameWjxServiceImpl extends BaseService implements IGameWjxService {

	@Autowired
	private GameWjxMapper gameWjxMapper;

	@Override
	public List<GameDTO> listGames(GameDTO gameDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<GameDTO> dtos = new ArrayList<>();
		GamePO gamePO = new GamePO();
		BeanUtils.copyProperties(gameDTO, gamePO);

		try {
			List<GamePO> pos = gameWjxMapper.listGames(gamePO);
			dtos = EntityUtils.exchange(pos, GameDTO.class.getName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dtos;
	}

	@Override
	public List<GameWjxScoreDTO> listWjxScore(GameWjxScoreDTO wjxScoreDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<GameWjxScoreDTO> dtos = new ArrayList<>();
		WjxScorePO wjxScorePO = new WjxScorePO();
		BeanUtils.copyProperties(wjxScoreDTO, wjxScorePO);

		try {
			List<WjxScorePO> pos = gameWjxMapper.listWjxScore(wjxScorePO);
			dtos = EntityUtils.exchange(pos, GameWjxScoreDTO.class.getName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

	@Override
	public int addUserRelGame(GameDTO gameDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		WjxScorePO wjxScorePO = new WjxScorePO();
		wjxScorePO.setUserId(gameDTO.getUserId());
		wjxScorePO.setGameId(gameDTO.getId());
		wjxScorePO.setTeacher(gameDTO.getTeacher());
		wjxScorePO.setFlag(1);

		int result = -1;
		try {
			result = gameWjxMapper.addUserRelGame(wjxScorePO);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public GameWjxInfoDTO getInitInfo(GameWjxInfoDTO gameInfoDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		GameWjxInfoDTO dto = new GameWjxInfoDTO();
		GameInfoPO gameInfoPO = new GameInfoPO();

		try {
			BeanUtils.copyProperties(gameInfoDTO, gameInfoPO);
			gameInfoPO = gameWjxMapper.getInitInfo(gameInfoPO);
			BeanUtils.copyProperties(gameInfoPO, dto);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dto;
	}

	@Override
	@Transactional
	public PageGameWjxDTO listRank(int gameGroupNo, int flag, int page, int size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		PageGameWjxDTO pageGameWjxDTO = new PageGameWjxDTO();

		try {
			List<GameInfoPO> pos = gameWjxMapper.listRank(gameGroupNo, flag, page, size);
			List<GameWjxInfoDTO> dtos = EntityUtils.exchange(pos, GameWjxInfoDTO.class.getName());
			pageGameWjxDTO.setDtos(dtos);
			int total = gameWjxMapper.countRank(gameGroupNo, flag, page, size);
			pageGameWjxDTO.setTotal(total);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return pageGameWjxDTO;
	}
	
	@Override
	@Transactional
	public GameWjxInfoDTO myRank(int gameGroupNo, int flag, int userId) {

		List<GameWjxInfoDTO> dtos = null;
		GameWjxInfoDTO gameWjxInfoDTO = new GameWjxInfoDTO();
		try {
			List<GameInfoPO> pos = gameWjxMapper.myRank(gameGroupNo, flag, userId);
			dtos = EntityUtils.exchange(pos, GameWjxInfoDTO.class.getName());

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		gameWjxInfoDTO = dtos.get(0);

		return gameWjxInfoDTO;
	}

}

package com.edudigital.cloudy.amp.textbook.service.service;

import java.util.List;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxInfoDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxScoreDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PageGameWjxDTO;

public interface IGameWjxService {
	/**
	 * 查询活动、游戏
	 * 
	 * @param gamePO
	 * @return
	 */
	List<GameDTO> listGames(GameDTO gameDTO);

	/**
	 * 查询问卷星分数
	 * 
	 * @param userPO
	 * @param gamePO
	 * @return
	 */
	List<GameWjxScoreDTO> listWjxScore(GameWjxScoreDTO wjxScoreDTO);

	/**
	 * 添加用户游戏（活动）关系
	 * 
	 * @param wjxScoreDTO
	 * @return
	 */
	int addUserRelGame(GameDTO gameDTO);

	/**
	 * 获取游、活动初始化信息
	 * 
	 * @param gameInfoDTO
	 * @return
	 */
	GameWjxInfoDTO getInitInfo(GameWjxInfoDTO gameInfoDTO);

	/**
	 * 
	 * @param gameGroupNo
	 * @param flag
	 * @param page
	 * @param size
	 * @return
	 */
	PageGameWjxDTO listRank(int gameGroupNo, int flag, int page, int size);
	
	/****
	 * 
	 * @param gameGroupNo
	 * @param flag
	 * @param userId
	 * @return
	 */
	GameWjxInfoDTO myRank(int gameGroupNo, int flag, int userId);
}

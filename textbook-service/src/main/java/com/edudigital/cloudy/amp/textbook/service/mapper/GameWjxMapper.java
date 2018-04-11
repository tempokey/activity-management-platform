package com.edudigital.cloudy.amp.textbook.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.textbook.service.entity.po.GameInfoPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.GamePO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.WjxScorePO;

@Service
public interface GameWjxMapper {

	/**
	 * 查询活动、游戏
	 * 
	 * @param gamePO
	 * @return
	 */
	List<GamePO> listGames(GamePO gamePO);

	/**
	 * 查询问卷星分数
	 * 
	 * @param userPO
	 * @param gamePO
	 * @return
	 */
	List<WjxScorePO> listWjxScore(WjxScorePO wjxScorePO);

	/**
	 * 添加用户、游戏（活动）关系
	 * 
	 * @param wjxScorePO
	 * @return
	 */
	int addUserRelGame(WjxScorePO wjxScorePO);

	/**
	 * 获取游戏、活动初始化数据
	 * 
	 * @param gameInfoDTO
	 * @return
	 */
	GameInfoPO getInitInfo(GameInfoPO gameInfoPO);

	/**
	 * 
	 * @param gameGroupNo
	 * @param flag
	 * @param page
	 * @param size
	 * @return
	 */
	List<GameInfoPO> listRank(@Param("gameGroupNo") int gameGroupNo, @Param("flag") int flag, @Param("page") int page,
			@Param("size") int size);

	/**
	 * 
	 * @param gameGroupNo
	 * @param flag
	 * @param page
	 * @param size
	 * @return
	 */
	int countRank(@Param("gameGroupNo") int gameGroupNo, @Param("flag") int flag, @Param("page") int page,
			@Param("size") int size);
	
	/****
	 * 
	 * @param gameGroupNo
	 * @param flag
	 * @param userId
	 * @return
	 */
	List<GameInfoPO> myRank(@Param("gameGroupNo") int gameGroupNo, @Param("flag") int flag,
			@Param("userId") int userId);
}

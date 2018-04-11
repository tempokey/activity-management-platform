package com.edudigital.cloudy.amp.textbook.custom.service.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxInfoDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxScoreDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PageGameWjxDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.IGameService;

@Component
public class GameServiceFallback implements IGameService {

	@Override
	public List<GameDTO> listGames(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameWjxScoreDTO> listWjxScore(Integer userId, Integer gameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addUserRelGame(GameDTO gameDTO) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public GameWjxInfoDTO getInitInfo(Integer userId, Integer gameGroupNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageGameWjxDTO listRank(Integer gameGroupNo, Integer flag, Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameWjxInfoDTO myRank(Integer gameGroupNo, Integer flag, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}

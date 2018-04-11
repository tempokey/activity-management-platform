package com.edudigital.cloudy.amp.camp.custom.dao;

import java.util.List;

import com.edudigital.cloudy.amp.camp.base.entity.MatchScoreDTO;

public interface IMatchScoreDAO {

	List<MatchScoreDTO> listMatchScore(int matchId, int itemId, int page, int size);

	int countMatchScore(int matchId, int itemId);
}

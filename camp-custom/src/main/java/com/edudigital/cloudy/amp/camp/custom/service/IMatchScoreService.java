package com.edudigital.cloudy.amp.camp.custom.service;

import java.util.List;

import com.edudigital.cloudy.amp.camp.base.entity.MatchScoreDTO;

public interface IMatchScoreService {

	List<MatchScoreDTO> listMatchScore(int matchId, int itemId, int page, int size);

	int countMatchScore(int matchId, int itemId);
}

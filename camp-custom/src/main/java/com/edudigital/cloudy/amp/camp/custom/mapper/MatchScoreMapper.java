package com.edudigital.cloudy.amp.camp.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.camp.custom.entity.po.MatchScorePO;

@Component
public interface MatchScoreMapper {

	/**
	 * 
	 * @param matchId
	 * @return
	 */
	List<MatchScorePO> listMatchScore(@Param("matchId") int matchId, @Param("itemId") int itemId,
			@Param("page") int page, @Param("size") int size);

	/**
	 * 
	 * @param matchId
	 * @param itemId
	 * @return
	 */
	int countMatchScore(@Param("matchId") int matchId, @Param("itemId") int itemId);
}

package com.edudigital.cloudy.amp.camp.custom.service;

import java.util.List;

import com.edudigital.cloudy.amp.camp.base.entity.MatchItemDTO;

public interface IMatchItemService {

	/**
	 * 获取比赛项目
	 * 
	 * @param MatchKindDTO
	 * @return
	 */
	List<MatchItemDTO> listMatchItems();
}

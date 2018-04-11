package com.edudigital.cloudy.amp.camp.custom.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.camp.custom.entity.po.MatchItemPO;

@Component
public interface MatchItemMapper {
	/**
	 * 查询所有比赛项目
	 * 
	 * @param MatchItemPO
	 * @return
	 */
	List<MatchItemPO> listMatchItems();
}

package com.edudigital.cloudy.amp.camp.custom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.camp.base.entity.MatchItemDTO;
import com.edudigital.cloudy.amp.camp.base.entity.MatchScoreDTO;
import com.edudigital.cloudy.amp.camp.custom.service.IMatchItemService;
import com.edudigital.cloudy.amp.camp.custom.service.IMatchScoreService;
import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;
import com.edudigital.cloudy.amp.util.base.util.res.ResUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/****
 * 
 * @author Yangs
 *
 */
@Api(value = "Match API Docs")
@CrossOrigin
@RestController
@RequestMapping(value = "/match/")
public class MatchController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4643575997858535193L;

	@Autowired
	private IMatchItemService matchItemService;

	@Autowired
	private IMatchScoreService matchScoreService;

	@ApiOperation(value = "查询赛项", notes = "移动端查询赛项接口")
	@RequestMapping(value = "item/listMatchItems", method = { RequestMethod.GET })
	public void listMatchItems(HttpServletRequest req, HttpServletResponse res) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<MatchItemDTO> dtos = matchItemService.listMatchItems();

		ResUtils.toRes(res, ResCode.SUCCESS, dtos);
	}

	@ApiOperation(value = "查询比赛成绩", notes = "移动端查询比赛成绩接口")
	@RequestMapping(value = "score/listMatchScore", method = { RequestMethod.GET })
	public void listMatchScore(HttpServletRequest req, HttpServletResponse res, String matchId, String itemId,
			String page, String size) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int _matchId = 0, _itemId = 0, _page = 1, _size = 10;
		try {
			_matchId = StringUtils.isNotBlank(matchId) ? Integer.parseInt(matchId) : 0;
			_itemId = StringUtils.isNotBlank(itemId) ? Integer.parseInt(itemId) : 0;
			_page = StringUtils.isNotBlank(page) && Integer.parseInt(page) > 0 ? Integer.parseInt(page) : 1;
			_size = StringUtils.isNotBlank(size) && Integer.parseInt(size) > 0 ? Integer.parseInt(size) : 10;
		} catch (Exception e) {
			logger.error(e.getMessage());

			ResUtils.toRes(res, ResCode.FAILURE_NOT_MATCHES);

			return;
		}

		List<MatchScoreDTO> dtos = matchScoreService.listMatchScore(_matchId, _itemId, _page, _size);

		ResUtils.toRes(res, ResCode.SUCCESS, dtos);
	}
}

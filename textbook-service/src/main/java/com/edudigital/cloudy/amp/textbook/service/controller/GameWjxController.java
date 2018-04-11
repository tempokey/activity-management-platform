package com.edudigital.cloudy.amp.textbook.service.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxInfoDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxScoreDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PageGameWjxDTO;
import com.edudigital.cloudy.amp.textbook.service.service.IGameWjxService;

import io.swagger.annotations.Api;

@CrossOrigin
@Api("Game WJS API Docs")
@RestController
@RequestMapping(value = "/game/")
public class GameWjxController extends BaseController {

	@Autowired
	private IGameWjxService gameWjxService;

	@RequestMapping(value = "listGames", method = RequestMethod.GET)
	public List<GameDTO> listGames(HttpServletRequest request, HttpServletResponse response, GameDTO gameDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		if (null == gameDTO) {
			logger.info("gameDto is null.");
			return null;
		}
		List<GameDTO> dtos = gameWjxService.listGames(gameDTO);

		return dtos;
	}

	/**
	 * 查询问卷星分数
	 * 
	 * @param userPO
	 * @param gamePO
	 * @return
	 */
	@RequestMapping(value = "listWjxScore", method = RequestMethod.GET)
	public List<GameWjxScoreDTO> listWjxScore(HttpServletRequest request, HttpServletResponse response,
			GameWjxScoreDTO wjxScoreDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		if (null == wjxScoreDTO) {
			logger.info("wjxScoreDTO is null.");
			return null;
		}

		List<GameWjxScoreDTO> dtos = gameWjxService.listWjxScore(wjxScoreDTO);

		return dtos;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param wjxScoreDTO
	 * @return
	 */
	@RequestMapping(value = "addUserRelGame", method = RequestMethod.POST)
	public int addUserRelGame(HttpServletRequest request, HttpServletResponse response, @RequestBody GameDTO gameDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		if (null == gameDTO) {
			logger.info("wjxScoreDTO is null.");
			return -1;
		}

		return gameWjxService.addUserRelGame(gameDTO);
	}

	@RequestMapping(value = "getInitInfo", method = RequestMethod.GET)
	public GameWjxInfoDTO getInitInfo(HttpServletRequest request, HttpServletResponse response,
			GameWjxInfoDTO gameInfoDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		if (null == gameInfoDTO) {
			logger.info("gameInfoDTO is null.");
			return null;
		}

		return gameWjxService.getInitInfo(gameInfoDTO);
	}

	@RequestMapping(value = "listRank", method = RequestMethod.GET)
	public PageGameWjxDTO listRank(HttpServletRequest request, HttpServletResponse response, String gameGroupNo,
			String flag, String page, String size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		PageGameWjxDTO pageGameWjxDTO = new PageGameWjxDTO();
		int _gameGroupNo, _flag, _page, _size;

		try {
			_gameGroupNo = Integer.parseInt(gameGroupNo);
			_flag = Integer.parseInt(flag);
			_page = Integer.parseInt(page);
			_size = Integer.parseInt(size);
			pageGameWjxDTO = gameWjxService.listRank(_gameGroupNo, _flag, _page, _size);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return pageGameWjxDTO;
	}
	
	@RequestMapping(value = "myRank", method = RequestMethod.GET)
	public GameWjxInfoDTO myRank(HttpServletRequest request, HttpServletResponse response, String gameGroupNo,
			String flag, String userId) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		GameWjxInfoDTO gameWjxInfoDTO = null;
		int _gameGroupNo, _flag, _userId;

		try {
			_gameGroupNo = Integer.parseInt(gameGroupNo);
			_flag = Integer.parseInt(flag);
			_userId = Integer.parseInt(userId);
			gameWjxInfoDTO = gameWjxService.myRank(_gameGroupNo, _flag, _userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return gameWjxInfoDTO;
	}
}

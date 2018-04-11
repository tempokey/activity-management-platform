package com.edudigital.cloudy.amp.textbook.custom.controller.game;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameDTO;
import com.edudigital.cloudy.amp.textbook.custom.controller.BaseController;
import com.edudigital.cloudy.amp.textbook.custom.service.IGameService;
import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;
import com.edudigital.cloudy.amp.util.base.util.res.ResUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin(origins = "*")
@Api("E-Book Game API Docs")
@RestController
@RequestMapping(value = "/activity/")
public class ActivityController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IGameService gameService;

	@ApiOperation(value = "获取游戏、活动列表", notes = "通用接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "游戏、活动对象", required = false, dataType = "int", paramType = "query") })
	@RequestMapping(value = "listGames", method = { RequestMethod.GET })
	public ResDTO listGames(HttpServletRequest req, HttpServletResponse res, @ApiIgnore GameDTO gameDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<GameDTO> dtos = new ArrayList<>();
		try {
			dtos = gameService.listGames(gameDTO.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResUtils.toRes(ResCode.SUCCESS, dtos);
	}

}

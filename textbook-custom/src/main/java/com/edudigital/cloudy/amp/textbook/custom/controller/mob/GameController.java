package com.edudigital.cloudy.amp.textbook.custom.controller.mob;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxInfoDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxScoreDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PageGameWjxDTO;
import com.edudigital.cloudy.amp.textbook.custom.controller.BaseController;
import com.edudigital.cloudy.amp.textbook.custom.service.IGameService;
import com.edudigital.cloudy.amp.textbook.custom.service.IUserService;
import com.edudigital.cloudy.amp.textbook.custom.util.EntityUtils;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;
import com.edudigital.cloudy.amp.util.base.util.res.ResUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin(origins = "*")
@Api("E-Book Game Mobile API Docs")
@RestController
@RequestMapping(value = "/mob/game/")
public class GameController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7599189581931961060L;

	@Autowired
	private IGameService gameService;

	@Autowired
	private IUserService userService;

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

	@ApiOperation(value = "获取问卷星成绩接口", notes = "通用接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "gameId", value = "组号", required = false, dataType = "int", paramType = "query") })
	@RequestMapping(value = "listWjsScore", method = { RequestMethod.GET })
	public ResDTO listWjsScore(HttpServletRequest req, HttpServletResponse res,
			@ApiIgnore GameWjxScoreDTO wjxScoreDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<GameWjxScoreDTO> dtos = new ArrayList<>();
		try {
			dtos = gameService.listWjxScore(wjxScoreDTO.getUserId(), wjxScoreDTO.getGameId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ResUtils.toRes(ResCode.SUCCESS, dtos);
	}

	@RequestMapping(value = "getInitInfo", method = { RequestMethod.GET })
	public String getInitInfo(HttpServletRequest req, HttpServletResponse res, UserDTO userDTO, String gameGroupNo,
			String callback) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		GameWjxInfoDTO gameInfoDTO = null;
		Map<String, Object> map = null;
		ResDTO resDTO = null;
		JSONArray jsonArray = new JSONArray();

		try {
			UserDTO user_ = new UserDTO();
			if (null != userDTO && StringUtils.isNotBlank(userDTO.getAccount())
					&& StringUtils.isNoneBlank(userDTO.getAdCode())) {
				user_.setAccount(userDTO.getAccount());
				user_.setAdCode(userDTO.getAdCode());
				userService.updateUser(user_);
			}
			userDTO.setAdCode(null);

			// 先获取用户库信息，再抓取活动库相关信息
			userDTO = userService.getUser(EntityUtils.instance2ObjMap(userDTO));
			gameInfoDTO = gameService.getInitInfo(userDTO.getId(), new Integer(gameGroupNo));
			gameInfoDTO.setUserId(userDTO.getId());
			map = EntityUtils.instanceNotNull2ObjMap(gameInfoDTO);
			resDTO = ResUtils.toRes(ResCode.SUCCESS, map);
			jsonArray.add(resDTO);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return callback + "(" + jsonArray.toString() + ");";
		// return ResUtils.toRes(ResCode.SUCCESS, map);
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "listRank", method = { RequestMethod.GET })
	public ResDTO listRank(HttpServletRequest req, HttpServletResponse res, String gameGroupNo, String flag,
			String page, String size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int _page = 1, _size = 10, _gameGroupNo, _flag;
		PageGameWjxDTO pageGameWjxDTO = new PageGameWjxDTO();
		try {
			_page = StringUtils.isNotBlank(page) && Integer.parseInt(page) > 0 ? Integer.parseInt(page) : 1;
			_size = StringUtils.isNotBlank(size) && Integer.parseInt(size) > 0 ? Integer.parseInt(size) : 10;
			_gameGroupNo = Integer.parseInt(gameGroupNo);
			_flag = Integer.parseInt(flag);
			pageGameWjxDTO = gameService.listRank(_gameGroupNo, _flag, (_page - 1) * _size, _size);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

		return ResUtils.toRes(ResCode.SUCCESS, new JSONArray().fromObject(pageGameWjxDTO));
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "myRank", method = { RequestMethod.GET })
	public ResDTO myRank(HttpServletRequest req, HttpServletResponse res, String gameGroupNo, String flag,
			String userId) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int _gameGroupNo = Integer.parseInt(gameGroupNo);
		int _flag = Integer.parseInt(flag);
		int _userId = Integer.parseInt(userId);
		GameWjxInfoDTO gameWjxInfoDTO = new GameWjxInfoDTO();
		try {
			gameWjxInfoDTO = gameService.myRank(_gameGroupNo, _flag, _userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

		return ResUtils.toRes(ResCode.SUCCESS, new JSONArray().fromObject(gameWjxInfoDTO));
	}

}

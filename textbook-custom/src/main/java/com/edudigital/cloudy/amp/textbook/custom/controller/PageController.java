package com.edudigital.cloudy.amp.textbook.custom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.IGameService;

@Controller
@RequestMapping(value = "/page/")
public class PageController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5333056282938680749L;

	@Autowired
	private IGameService gameService;

	@RequestMapping(value = "showGame")
	public String showGame(HttpServletRequest request, HttpServletResponse response) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return "wjx/game";
	}

	@RequestMapping(value = "getGameRules")
	public String getGameRules(HttpServletRequest request, HttpServletResponse response) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return "wjx/gameRules";
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @param gameDTO
	 *            手机如果获取改用户教师传教师字段
	 * @param ack
	 *            手机访问1 网页访问 不填
	 * @return
	 */
	@RequestMapping(value = "joinGame")
	public String joinGame(HttpServletRequest req, HttpServletResponse res, GameDTO gameDTO, String ack, String url) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		if (StringUtils.isEmpty(url)) {

			return "error/404";
		}

		if (gameDTO != null && StringUtils.isNotBlank(gameDTO.getTeacher())) {
			if (StringUtils.isBlank(ack)) {
				try {
					gameService.addUserRelGame(gameDTO);
				} catch (Exception e) {
					logger.error(e.getMessage());
					return "error/404";
				}
			}
			return "redirect:" + url + gameDTO.getUserId();
		} else {
			req.setAttribute("userId", gameDTO.getUserId());
			req.setAttribute("id", gameDTO.getId());
			req.setAttribute("url", url);
			return "wjx/teacher";
		}
	}

	@RequestMapping(value = "getRank")
	public String getRank(HttpServletRequest request, HttpServletResponse response, String userId, String gameGroupNo) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		if (StringUtils.isEmpty(userId)) {

			return "error/404";
		}
		request.setAttribute("page", 1);
		request.setAttribute("size", 10);
		request.setAttribute("userId", userId);
		request.setAttribute("gameGroupNo", gameGroupNo);
		request.setAttribute("flag", 3);
		return "wjx/rank";
	}

}

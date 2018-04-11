package com.edudigital.cloudy.amp.camp.custom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edudigital.cloudy.amp.camp.base.entity.MatchItemDTO;
import com.edudigital.cloudy.amp.camp.base.entity.MatchScoreDTO;
import com.edudigital.cloudy.amp.camp.custom.service.IMatchItemService;
import com.edudigital.cloudy.amp.camp.custom.service.IMatchScoreService;

@Controller
@RequestMapping(value = "/match/page/")
public class PageController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4157075745832819328L;

	@Autowired
	private IMatchItemService matchItemService;

	@Autowired
	private IMatchScoreService matchScoreService;

	@RequestMapping(value = "matchItems")
	public String matchItems(HttpServletRequest req, HttpServletResponse res) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<MatchItemDTO> dtos = matchItemService.listMatchItems();
		req.setAttribute("miDtos", dtos);

		return "matchItems";
	}

	@Transactional
	@RequestMapping(value = "matchScore/{itemId}")
	public String matchScore(HttpServletRequest req, HttpServletResponse res, @PathVariable String itemId,
			String matchId, String page, String size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int _matchId = 0, _itemId = 0, _page = 1, _size = 10;
		try {
			_matchId = StringUtils.isNotBlank(matchId) ? Integer.parseInt(matchId) : 0;
			_itemId = StringUtils.isNotBlank(itemId) ? Integer.parseInt(itemId) : 0;
			_page = StringUtils.isNotBlank(page) && Integer.parseInt(page) > 0 ? Integer.parseInt(page) : 1;
			_size = StringUtils.isNotBlank(size) && Integer.parseInt(size) > 0 ? Integer.parseInt(size) : 10;

		} catch (Exception e) {
			logger.error(e.getMessage());
			req.setAttribute(this.P_ERROR_MSG_KEY, e.getMessage());
			return this.P_ERROR_VIEW;
		}

		List<MatchScoreDTO> dtos = matchScoreService.listMatchScore(_matchId, _itemId, (_page - 1) * _size, _size);
		int totalMatchScore = matchScoreService.countMatchScore(_matchId, _itemId);
		req.setAttribute("page", _page);
		req.setAttribute("size", _size);
		req.setAttribute("itemId", itemId);
		req.setAttribute("msDtos", dtos);
		req.setAttribute("msTotal", totalMatchScore);

		return "matchScore";
	}

	@RequestMapping(value = "{rest}")
	public String rest(HttpServletRequest req, HttpServletResponse res, @PathVariable String rest) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return rest;
	}

	@RequestMapping(value = "index2")
	public String index() {
		return "demo";
	}

}

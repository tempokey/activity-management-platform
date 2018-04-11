package com.edudigital.cloudy.amp.news.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.news.base.entity.dto.NewsDTO;
import com.edudigital.cloudy.amp.news.service.entity.po.NewsPO;
import com.edudigital.cloudy.amp.news.service.service.INewsService;

@CrossOrigin
@RestController
@RequestMapping(value = "/news")
public class NewsController extends BaseController {

	@Autowired
	private INewsService newsService;

	@RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
	public String helloWorld(HttpServletRequest req, HttpServletResponse res) {
		logger.info("Hello World");
		return "Hello World";
	}

	@RequestMapping(value = "/addNews", method = RequestMethod.POST)
	public String addNews(HttpServletRequest req, HttpServletResponse res, NewsDTO newsDTO) {
		logger.info("addNews");
		NewsPO newsPO = new NewsPO();
		try {
			BeanUtils.copyProperties(newsDTO, newsPO);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		newsService.addNews(newsPO);
		return "addNews";
	}

	@RequestMapping(value = "/saveNews", method = RequestMethod.POST)
	public String saveNews(HttpServletRequest req, HttpServletResponse res, @RequestBody NewsDTO newsDTO) {
		logger.info("addNews");
		NewsPO newsPO = new NewsPO();
		try {
			BeanUtils.copyProperties(newsDTO, newsPO);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		newsService.saveNews(newsPO);
		return "saveNews";
	}

	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String test1(@RequestParam String test) {
		return "test1";
	}
}

package com.edudigital.cloudy.amp.news.custom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.news.base.entity.dto.NewsDTO;
import com.edudigital.cloudy.amp.news.custom.service.INewsService;

@CrossOrigin
@RestController
@RequestMapping(value = "/news")
public class NewsController extends BaseController {

	@Autowired
	private INewsService newsService;

	@RequestMapping(value = "/saveNews", method = RequestMethod.GET)
	public String saveNews(HttpServletRequest req, HttpServletResponse res, NewsDTO newsDTO) {
		String str = newsService.saveNews(newsDTO);
		return str;
	}

	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String test1(HttpServletRequest req, HttpServletResponse res, String test) {
		String str = newsService.test1(test);
		return str;
	}

}

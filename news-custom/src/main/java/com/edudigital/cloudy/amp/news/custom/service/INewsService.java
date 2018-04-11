package com.edudigital.cloudy.amp.news.custom.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edudigital.cloudy.amp.news.base.entity.dto.NewsDTO;

@FeignClient(name = "news-service")
public interface INewsService {

	@RequestMapping(value = "/news-service/news/saveNews", method = RequestMethod.POST)
	public String saveNews(@RequestBody NewsDTO newsDTO);

	@RequestMapping(value = "/news-service/news/test1", method = RequestMethod.GET)
	public String test1(@RequestParam("test") String test);

}

package com.edudigital.cloudy.amp.news.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.news.service.entity.po.NewsPO;
import com.edudigital.cloudy.amp.news.service.repo.mongo.INewsRepository;
import com.edudigital.cloudy.amp.news.service.service.BaseService;
import com.edudigital.cloudy.amp.news.service.service.INewsService;

@Service
public class NewsServiceImpl extends BaseService implements INewsService {

	@Autowired
	private INewsRepository newsRepo;

	@Override
	public void saveNews(NewsPO newsPO) {
		mongoTemplate.save(newsPO);
	}

	@Override
	public void addNews(NewsPO newsPO) {
		newsRepo.save(newsPO);
	}

}

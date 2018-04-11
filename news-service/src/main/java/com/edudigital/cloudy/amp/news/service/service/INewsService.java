package com.edudigital.cloudy.amp.news.service.service;

import com.edudigital.cloudy.amp.news.service.entity.po.NewsPO;

public interface INewsService {

	void saveNews(NewsPO newsPO);

	void addNews(NewsPO newsPO);

}

package com.edudigital.cloudy.amp.news.service.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.edudigital.cloudy.amp.news.service.entity.po.NewsPO;

@Repository
public interface INewsRepository extends MongoRepository<NewsPO, String> {

}

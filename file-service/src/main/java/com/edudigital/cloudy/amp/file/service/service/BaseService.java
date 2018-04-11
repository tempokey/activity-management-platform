package com.edudigital.cloudy.amp.file.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class BaseService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected MongoTemplate mongoTemplate;

}

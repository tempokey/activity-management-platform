package com.edudigital.cloudy.amp.file.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.edudigital.cloudy.amp.file.service.service.BaseYamlValues;

@ConfigurationProperties(prefix = "file.upload")
public abstract class BaseController extends BaseYamlValues {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected DiscoveryClient discoveryClient;
	@Autowired
	protected MongoTemplate mongoTemplate;

	protected String uploadFilePath;
}

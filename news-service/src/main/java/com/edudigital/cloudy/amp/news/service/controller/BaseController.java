package com.edudigital.cloudy.amp.news.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected DiscoveryClient discoveryClient;
}

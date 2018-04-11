package com.edudigital.cloudy.amp.msg.service.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

public abstract class BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5068167935128933404L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected DiscoveryClient discoveryClient;
}

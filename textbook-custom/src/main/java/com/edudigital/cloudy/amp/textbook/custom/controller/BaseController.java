package com.edudigital.cloudy.amp.textbook.custom.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 576001076589717530L;

	public Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${wjx.request.url}")
	private String wjxRequestUrl;

	public String getWjxRequestUrl() {
		return wjxRequestUrl;
	}

	public void setWjxRequestUrl(String wjxRequestUrl) {
		this.wjxRequestUrl = wjxRequestUrl;
	}

}

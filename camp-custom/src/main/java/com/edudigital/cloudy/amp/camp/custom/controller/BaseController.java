package com.edudigital.cloudy.amp.camp.custom.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file.upload")
public abstract class BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7436156935919740809L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	final String P_ERROR_VIEW = "error";
	final String P_ERROR_MSG_KEY = "errorMsg";

	protected String uploadFilePath;

}

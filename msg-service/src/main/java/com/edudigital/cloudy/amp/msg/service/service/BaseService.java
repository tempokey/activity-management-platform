package com.edudigital.cloudy.amp.msg.service.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4505809633897415144L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

}

package com.edudigital.cloudy.amp.textbook.service.entity.bo;

import java.io.Serializable;

public abstract class BaseBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

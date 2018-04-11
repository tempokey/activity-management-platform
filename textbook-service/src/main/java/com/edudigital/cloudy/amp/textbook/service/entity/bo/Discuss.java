package com.edudigital.cloudy.amp.textbook.service.entity.bo;

import java.util.List;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.DiscussDTO;

/**
 * 讨论 Entity
 * 
 * @author chenzq
 * @version 2017-10-10
 */
public class Discuss extends BaseBO {

	private static final long serialVersionUID = 1L;

	private List<DiscussDTO> discuss;

	public List<DiscussDTO> getDiscuss() {
		return discuss;
	}

	public void setDiscuss(List<DiscussDTO> discuss) {
		this.discuss = discuss;
	}



}

package com.edudigital.cloudy.amp.textbook.base.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class PageGameWjxDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3290067582523249705L;

	private int total;

	private List<GameWjxInfoDTO> dtos = new ArrayList<>();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<GameWjxInfoDTO> getDtos() {
		return dtos;
	}

	public void setDtos(List<GameWjxInfoDTO> dtos) {
		this.dtos = dtos;
	}

}

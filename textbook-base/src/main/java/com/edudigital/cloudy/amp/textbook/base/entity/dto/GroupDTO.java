package com.edudigital.cloudy.amp.textbook.base.entity.dto;

import java.util.List;

public class GroupDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int groupId;

	private String group;

	private List<SchoolDTO> schools;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<SchoolDTO> getSchools() {
		return schools;
	}

	public void setSchools(List<SchoolDTO> schools) {
		this.schools = schools;
	}

}

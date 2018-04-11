package com.edudigital.cloudy.amp.textbook.service.entity.bo;

import java.util.List;

public class GroupBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int groupCode;

	private String group;

	private List<SchoolBO> schools;

	public int getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<SchoolBO> getSchools() {
		return schools;
	}

	public void setSchools(List<SchoolBO> schools) {
		this.schools = schools;
	}

}

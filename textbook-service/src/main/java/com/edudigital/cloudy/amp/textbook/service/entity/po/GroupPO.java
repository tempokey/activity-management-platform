package com.edudigital.cloudy.amp.textbook.service.entity.po;

import java.util.List;

public class GroupPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int groupCode;

	private String group;

	private List<SchoolPO> schools;

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

	public List<SchoolPO> getSchools() {
		return schools;
	}

	public void setSchools(List<SchoolPO> schools) {
		this.schools = schools;
	}

}

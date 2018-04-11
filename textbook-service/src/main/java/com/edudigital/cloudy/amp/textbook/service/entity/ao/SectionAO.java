package com.edudigital.cloudy.amp.textbook.service.entity.ao;

public class SectionAO extends BaseAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	private int contextId;
	// 节
	private String section;
	// 章id
	private String parentId;

	
	public int getContextId() {
		return contextId;
	}

	public void setContextId(int contextId) {
		this.contextId = contextId;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}

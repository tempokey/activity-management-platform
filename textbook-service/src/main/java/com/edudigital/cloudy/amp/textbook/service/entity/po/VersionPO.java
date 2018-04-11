package com.edudigital.cloudy.amp.textbook.service.entity.po;

public class VersionPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 版本编号
	private int verCode;
	// 项目名称
	private String project;
	// 版本号 A.B.C
	private String version;
	// 比较数值
	private int num;
	// 比较规则
	private String rule;

	public int getVerCode() {
		return verCode;
	}

	public void setVerCode(int verCode) {
		this.verCode = verCode;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}

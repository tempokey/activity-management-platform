package com.edudigital.cloudy.amp.camp.base.entity;

public class MatchScoreDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7511597743191754206L;

	private String stuId;
	private String score;
	private String stuName;
	private String stuNum;
	private String majorType;
	private String majorName;
	private String beginYear;
	private String className;
	private String chalgNum;
	private String lastTime;

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getMajorType() {
		return majorType;
	}

	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(String beginYear) {
		this.beginYear = beginYear;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getChalgNum() {
		return chalgNum;
	}

	public void setChalgNum(String chalgNum) {
		this.chalgNum = chalgNum;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

}

package com.edudigital.cloudy.amp.textbook.service.entity.bo;

import java.util.Date;
import java.util.List;

public class QuestionBO extends BaseBO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int questionCode;

	private String question;

	private int type;

	private int periodCode;

	private String periodId;

	private String resolution;

	private int contextCode;

	private String contextId;

	private Date createTime;

	private Date updateTime;

	private List<AskBO> asks;

	public int getPeriodCode() {
		return periodCode;
	}

	public void setPeriodCode(int periodCode) {
		this.periodCode = periodCode;
	}

	public String getPeriodId() {
		return periodId;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	public int getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(int questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public int getContextCode() {
		return contextCode;
	}

	public void setContextCode(int contextCode) {
		this.contextCode = contextCode;
	}

	public String getContextId() {
		return contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<AskBO> getAsks() {
		return asks;
	}

	public void setAsks(List<AskBO> asks) {
		this.asks = asks;
	}

}

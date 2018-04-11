package com.edudigital.cloudy.amp.textbook.service.entity.po;

import java.util.Date;
import java.util.List;

public class QuestionPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int questionId;

	private String question;
	// 类型 1 选择 2 判断
	private int type;
	
	private int periodId;

	private String resolution;

	private int contextCode;

	private String contextId;

	private Date createTime;

	private Date updateTime;

	private List<AskPO> asks;

	
	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
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

	public List<AskPO> getAsks() {
		return asks;
	}

	public void setAsks(List<AskPO> asks) {
		this.asks = asks;
	}

}

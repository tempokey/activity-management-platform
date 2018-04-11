package com.edudigital.cloudy.amp.textbook.service.entity.po;

import java.util.Date;

public class AskPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int askCode;
	// 选项
	private String ask;
	// 内容
	private String content;
	// 是否为该题答案
	private boolean answer;
	// 解析
	private String instruction;

	private int questionCode;

	private String questionId;

	private Date createTime;

	private Date updateTime;

	public int getAskCode() {
		return askCode;
	}

	public void setAskCode(int askCode) {
		this.askCode = askCode;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public String getResolution() {
		return instruction;
	}

	public void setResolution(String instruction) {
		this.instruction = instruction;
	}

	public int getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(int questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
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

}

package com.edudigital.cloudy.amp.textbook.service.entity.bo;

import java.util.Date;

/**
 * 讨论 Entity
 * 
 * @author chenzq
 * @version 2017-10-9
 */
public class DiscussBO extends BaseBO {

	private static final long serialVersionUID = 1L;
	// 讨论code
	private int discussCode;
	// 讨论题目
	private String discuss;
	// 目录code
	private int contextCode;
	// 目录id
	private String contextId;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;
	// 课时code
	private int periodCode;
	// 课时id
	private String periodId;

	public int getDiscussCode() {
		return discussCode;
	}

	public void setDiscussCode(int discussCode) {
		this.discussCode = discussCode;
	}

	public String getDiscuss() {
		return discuss;
	}

	public void setDiscuss(String discuss) {
		this.discuss = discuss;
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

}

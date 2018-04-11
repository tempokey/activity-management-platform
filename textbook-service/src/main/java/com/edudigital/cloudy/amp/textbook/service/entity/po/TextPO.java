package com.edudigital.cloudy.amp.textbook.service.entity.po;

import java.util.Date;

public class TextPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 文本code
	private int txtCode;
	// 文本内容
	private String txt;
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
	// 条目数
	private int entryCode;

	public int getTxtCode() {
		return txtCode;
	}

	public void setTxtCode(int txtCode) {
		this.txtCode = txtCode;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
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

	public int getEntryCode() {
		return entryCode;
	}

	public void setEntryCode(int entryCode) {
		this.entryCode = entryCode;
	}

}

package com.edudigital.cloudy.amp.textbook.service.entity.bo;

import java.util.Date;

public class ContextBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 目录Id
	private int contextId;
	// 图书ID
	private int bookId;
	// 目录内容
	private String context;
	// 目录顺序
	private int order;
	// 目录类型
	private int type;
	// 节对应章的id
	private int parentId;
	// 节Id
	private int chapterId;
	//权重
	private int weight;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getContextId() {
		return contextId;
	}

	public void setContextId(int contextId) {
		this.contextId = contextId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
}

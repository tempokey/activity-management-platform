package com.edudigital.cloudy.amp.textbook.service.entity.ao;

import java.util.Date;
import java.util.List;

import com.edudigital.cloudy.amp.textbook.service.entity.bo.ContextBO;

/**
 * 
 * @author chenzq
 * @date 2017-10-27
 */
public class ChapterContextAO extends BaseAO {

	private static final long serialVersionUID = 1L;

	// 目录code
	private int contextCode;
	// 图书code
	private int bookCode;
	// 图书ID
	private String bookId;
	// 目录内容
	private String context;
	// 目录顺序
	private int order;
	// 目录类型
	private int type;
	// 节对应章的id
	private int parentId;
	// 节编码
	private int chapterCode;
	// 权重
	private int weight;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;
	// 节
	List<ContextBO> contextBOs;

	public int getContextCode() {
		return contextCode;
	}

	public void setContextCode(int contextCode) {
		this.contextCode = contextCode;
	}

	public int getBookCode() {
		return bookCode;
	}

	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

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

	public int getChapterCode() {
		return chapterCode;
	}

	public void setChapterCode(int chapterCode) {
		this.chapterCode = chapterCode;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public List<ContextBO> getContextBOs() {
		return contextBOs;
	}

	public void setContextBOs(List<ContextBO> contextBOs) {
		this.contextBOs = contextBOs;
	}

}

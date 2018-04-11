package com.edudigital.cloudy.amp.textbook.base.entity.dto;

public class EbkDataDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 本地id
	private int dataCode;
	// 移动端数据
	private int bookId;
	// 1 章 2 节 3 内容
	private int type;
	// 内容
	private String content;
	//
	private int pageno;
	//
	private int parentId;
	// 0 无测试 1 有测试
	private int test;
	//
	private String remark;

	public int getDataCode() {
		return dataCode;
	}

	public void setDataCode(int dataCode) {
		this.dataCode = dataCode;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

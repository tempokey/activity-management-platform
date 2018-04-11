package com.edudigital.cloudy.amp.textbook.service.entity.po;

public class UserBkPO extends BasePO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int ubRelId;
	private String adCode;
	private int userId;
	private int bookId;
	public int getUbRelId() {
		return ubRelId;
	}
	public void setUbRelId(int ubRelId) {
		this.ubRelId = ubRelId;
	}
	public String getAdCode() {
		return adCode;
	}
	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

}

package com.edudigital.cloudy.amp.textbook.base.entity.ao;

import java.util.List;

/**
 * 书籍Entity
 * 
 * @author chenzq
 * @date 2018/02/01
 */
public class EbkAO extends BaseAO {
	private static final long serialVersionUID = 1L;
	// 图书Id
	private int bookId;
	// 书名
	private String book;
	// 课程编号
	private int majorId;
	// 作者
	private String author;
	// 出版社
	private String press;
	// 教材：1是精品课，2是普通书籍
	// 练习册：3是课程章节，4是课程题型，5书本章节，6书本题型，7口袋单词类型
	// （课程是进入之后需要选书，书籍是进入直接显示章节）
	private int typeId;
	
	private String type;
	// 父类
	private String tbParentId;
	// 封面地址
	private String cover;
	// 简介
	private String brief;
	// 价格
	private double price;
	// 促销价
	private double sale;
	// 下载地址
	private String path;
	// 是否已购买
	private int buy;

	private String remarks;

	private List<EbkAO> listEbkAO;
	// 专业
	private String major;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTbParentId() {
		return tbParentId;
	}

	public void setTbParentId(String tbParentId) {
		this.tbParentId = tbParentId;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<EbkAO> getListEbkAO() {
		return listEbkAO;
	}

	public void setListEbkAO(List<EbkAO> listEbkAO) {
		this.listEbkAO = listEbkAO;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}
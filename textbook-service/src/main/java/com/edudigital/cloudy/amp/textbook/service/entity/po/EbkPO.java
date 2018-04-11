package com.edudigital.cloudy.amp.textbook.service.entity.po;

import java.util.Date;

public class EbkPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 图书Id
	private int bookId;
	// 书名
	private String book;
	// 课程编号
	private int majorId;
	// 作者ID
	private int authorId;
	// 作者
	private String author;
	// 出版社id
	private int pressId;
	// 出版社
	private String press;
	// 类型ID
	private int typeId;
	// 教材：1是精品课，2是普通书籍
	// 练习册：3是课程章节，4是课程题型，5书本章节，6书本题型，7口袋单词类型
	// （课程是进入之后需要选书，书籍是进入直接显示章节）
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
	//
	private double divide;
	// 下载地址
	private String path;
	// 1上架2下架
	private int status;
	// 备注
	private String mark;
	// 下载量
	private int num;
	// 字符数
	private int length;
	// 版本
	private int version;
	// 版本附件信息
	private int attachment;
	// 是否已购买
	private int buy;
	// 创建时间
	private Date createTime;

	private Date updateTime;
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

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPressId() {
		return pressId;
	}

	public void setPressId(int pressId) {
		this.pressId = pressId;
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

	public double getDivide() {
		return divide;
	}

	public void setDivide(double divide) {
		this.divide = divide;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getAttachment() {
		return attachment;
	}

	public void setAttachment(int attachment) {
		this.attachment = attachment;
	}

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
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

	public String getTbParentId() {
		return tbParentId;
	}

	public void setTbParentId(String tbParentId) {
		this.tbParentId = tbParentId;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}
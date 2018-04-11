package com.edudigital.cloudy.amp.textbook.base.entity.dto;

public class PreOrderDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 支付方式
	private String payStyle;
	// 交易金额
	private String totalAmount;
	// 交易内容
	private String subject;
	// 产品编码
	private String productId;
	// 用户ID
	private int userId;
	// 爱丁号
	private String adCode;
	// 学生姓名
	private String userName;
	// 项目名称
	private String project;
	// 终端IP
	private String k_Spbill_Create_Ip;
	// 回调URL
	private String notify_url;

	public String getPayStyle() {
		return payStyle;
	}

	public void setPayStyle(String payStyle) {
		this.payStyle = payStyle;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getK_Spbill_Create_Ip() {
		return k_Spbill_Create_Ip;
	}

	public void setK_Spbill_Create_Ip(String k_Spbill_Create_Ip) {
		this.k_Spbill_Create_Ip = k_Spbill_Create_Ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}

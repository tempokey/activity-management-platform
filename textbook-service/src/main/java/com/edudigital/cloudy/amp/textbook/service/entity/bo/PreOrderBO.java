package com.edudigital.cloudy.amp.textbook.service.entity.bo;

public class PreOrderBO extends BaseBO {

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
	private String productCode;
	// 用户ID
	private String userId;
	// 用户code
	private int userCode;
	// 爱丁号
	private String adCode;
	// 学生姓名
	private String userName;
	// 项目名称
	private String project;

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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
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

}

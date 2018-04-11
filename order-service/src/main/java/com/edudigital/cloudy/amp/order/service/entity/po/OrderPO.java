package com.edudigital.cloudy.amp.order.service.entity.po;

import java.util.Date;

/**
 * 
 * @author Tempo
 * @date 2017年7月21日 上午9:34:39
 *
 */
public class OrderPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Id;
	private String parentId; // 父订单id
	private String projectId; // 项目id
	private String project;
	private String orderNo; // 订单号
	private String flowNo; // 流水号
	private String outFlowNo; // 退款流水号
	private String productId; // 产品id
	private String adCode;
	private int userId; // 用户ID
	private String context; // 内容
	private String method; // 支付方式 W微信 A支付宝
	private double price; // 价格
	private double refund; // 退款金额
	private int status; // 订单状态
	private int result; // 处理状态
	private Date createTime; // 订单创建时间
	private Date payTime; // 付款时间
	private Date updateTime; // 订单修改时间
	private Date commitTime; // 支付确认时间
	private Date refundTime; // 退款时间
	private Date closeTime; // 交易关闭时间
	private String mark; // 创建者
	private Date startTime;// 查询开始时间
	private Date endTime;// 查询截止时间

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getOutFlowNo() {
		return outFlowNo;
	}

	public void setOutFlowNo(String outFlowNo) {
		this.outFlowNo = outFlowNo;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRefund() {
		return refund;
	}

	public void setRefund(double refund) {
		this.refund = refund;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}

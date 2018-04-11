package com.edudigital.cloudy.amp.msg.base.constant.enumeration;

public enum MsgStatus {

	WAIT(1,"请求中"),
	SUCCESS(2,"成功"),
	DEFEAT(3,"失败");
	//	成员变量
	private int  statusCode;
	private String status;
	//	构造方法	

	private MsgStatus(int statusCode, String status) {
	this.statusCode = statusCode;
	this.status = status;
}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	

}

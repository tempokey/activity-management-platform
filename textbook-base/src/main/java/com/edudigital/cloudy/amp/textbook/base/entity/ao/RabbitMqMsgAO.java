package com.edudigital.cloudy.amp.textbook.base.entity.ao;

public class RabbitMqMsgAO extends BaseAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 唯一识别码
	private String code;
	// 辅助识别码
	private String key;
	// 项目名称
	private String project;
	// 消息
	private String msg;
	// 状态
	private String status;
	// 时间戳
	private Long timestamp;
	//用户编号
	private int userId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}

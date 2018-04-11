package com.edudigital.cloudy.amp.msg.service.entity.po;

import java.io.Serializable;

public class RabbitMqMsgPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2731589971208336931L;
	// 配置名称
	private String configName;
	// 配置值
	private String configValue;
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

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

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

}

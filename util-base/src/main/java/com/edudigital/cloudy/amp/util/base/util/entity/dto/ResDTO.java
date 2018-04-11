package com.edudigital.cloudy.amp.util.base.util.entity.dto;

import java.io.Serializable;

/**
 * 返回对象
 * 
 * @author Tempo
 * @date 2017年9月6日 上午10:29:20
 *
 */
public class ResDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1512582564635588017L;

	private String id = "id";
	//
	private String sessionid = "sessionid";
	// 返回码
	private int code;
	// 消息
	private String msg;
	// 数据
	private String data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}

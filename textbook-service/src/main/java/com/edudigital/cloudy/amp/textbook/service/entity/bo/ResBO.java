package com.edudigital.cloudy.amp.textbook.service.entity.bo;

/**
 * 返回对象
 * 
 * @author Tempo
 * @date 2017年9月6日 上午10:29:20
 *
 */
public class ResBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private String sessionid;
	// 返回码
	private int code;
	// 消息
	private String msg;
	// 数据
	private String data;

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

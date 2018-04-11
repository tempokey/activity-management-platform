package com.edudigital.cloudy.amp.msg.base.entity;

import java.io.Serializable;

import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MsgChannel;
import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MsgMethod;
import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MsgStatus;

public class MsgDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 短信ID
	private String msgID;
	// 短信渠道 默认阿里
	private MsgChannel msgChannel;
	// 短信方式
	private MsgMethod msgMethod;
	// 电话号码
	private String phones;
	//	请求状态 
	private MsgStatus msgStatus;

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public MsgChannel getMsgChannel() {
		return msgChannel;
	}

	public void setMsgChannel(MsgChannel msgChannel) {
		this.msgChannel = msgChannel;
	}

	public MsgMethod getMsgMethod() {
		return msgMethod;
	}

	public void setMsgMethod(MsgMethod msgMethod) {
		this.msgMethod = msgMethod;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public MsgStatus getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(MsgStatus msgStatus) {
		this.msgStatus = msgStatus;
	}

}

package com.edudigital.cloudy.amp.textbook.custom.entity.bo;

import java.io.Serializable;

import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MsgChannel;
import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MsgMethod;
import com.edudigital.cloudy.amp.msg.base.entity.MsgDTO;

public class MsgBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6456286020343477306L;
	// 短信ID
	private String msgID;
	// 短信方式
	private String msgMethod;
	// 电话号码
	private String phones;
	
	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public String getMsgMethod() {
		return msgMethod;
	}
	public void setMsgMethod(String msgMethod) {
		this.msgMethod = msgMethod;
	}
	public String getPhones() {
		return phones;
	}
	public void setPhones(String phones) {
		this.phones = phones;
	}

	public MsgDTO toDTO() {
		MsgDTO msgDTO = new MsgDTO();
		msgDTO.setMsgID(this.msgID);
		msgDTO.setPhones(this.phones);
		msgDTO.setMsgChannel(MsgChannel.ALI);
		for(MsgMethod msgMethod:MsgMethod.values()) {
			if(msgMethod.toString().equals(this.msgMethod)) {
				msgDTO.setMsgMethod(msgMethod);
				break;
			}
		}
		return msgDTO;
	}
	
}

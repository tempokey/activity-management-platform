package com.edudigital.cloudy.amp.msg.service.entity.po;

import java.io.Serializable;

/**
 * 模版编码
 * 
 * @author Tempo
 * @date 2018年1月19日 上午9:14:26
 *
 */
public class MsgTemplatePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2731589971208336931L;
	// id
	private int id;
	//
	private String channel;
	// 业务编码
	private String bizCode;
	// 模版编码
	private String templateCode;
	// 签名
	private String signName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

}

package com.edudigital.cloudy.amp.file.base.entity.dto;

import java.io.Serializable;

/**
 * 
 * @author chenzq
 * @date 2018/01/31
 */
public class FileDTO implements Serializable {

	private static final long serialVersionUID = 1512582564635588017L;
	// 字节流
	private byte[] buffer;
	// 文件名字
	private String fileName;
	// 返回码
	private int code;
	// 消息
	private String msg;
	public byte[] getBuffer() {
		return buffer;
	}

	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
}

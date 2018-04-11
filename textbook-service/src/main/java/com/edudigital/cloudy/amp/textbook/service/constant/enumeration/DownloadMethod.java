package com.edudigital.cloudy.amp.textbook.service.constant.enumeration;

public enum DownloadMethod {
	JSON(1, "json"), TXT(2, "txt");
	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private DownloadMethod(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (DownloadMethod o : DownloadMethod.values()) {
			if (o.getIndex() == index) {
				return o.type;
			}
		}
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public String toString() {
		return type;
	}
}

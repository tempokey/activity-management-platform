package com.edudigital.cloudy.amp.textbook.service.constant.enumeration;

public enum ReqKey {
	//
	FILE_NAME(1,"fileName"),
	
	OUT_TYPE(2,"outType");

	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private ReqKey(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (ReqKey o : ReqKey.values()) {
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

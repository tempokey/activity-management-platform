package com.edudigital.cloudy.amp.textbook.base.constant.enumeration;

public enum FileType {
	// txt
	TXT(1, ".txt"),
	// doc
	DOC(2, ".doc"),
	// docx
	DOCX(3, ".docx");
	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private FileType(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (FileType o : FileType.values()) {
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

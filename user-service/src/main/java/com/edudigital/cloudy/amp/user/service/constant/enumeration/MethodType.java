package com.edudigital.cloudy.amp.user.service.constant.enumeration;

/**
 * 函数类型
 * 
 * @author Tempo
 * @date 2017年9月5日 下午4:05:54
 *
 */
public enum MethodType {
	// handler
	HANDLER(1, "Handler-"),
	// service
	SERVICE(2, "Service-"),
	// dao
	DAO(3, "DAO-"),
	// map
	MAPPER(4, "Mapper-");
	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private MethodType(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (MethodType o : MethodType.values()) {
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

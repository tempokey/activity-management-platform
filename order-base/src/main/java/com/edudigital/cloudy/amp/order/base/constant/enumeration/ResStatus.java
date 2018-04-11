package com.edudigital.cloudy.amp.order.base.constant.enumeration;

/**
 * 返回值
 * 
 * @author Tempo
 * @date 2017年8月15日 下午2:46:36
 *
 */
public enum ResStatus {
	// 成功
	SUCCESS(1000, "success"),
	// 存在
	EXIST(2000, "exist"),
	// 不存在
	UNDEFINED(4000, "undefined"),
	// 失败
	ERROR(5000, "error");
	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private ResStatus(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (ResStatus o : ResStatus.values()) {
			if (o.getIndex() == index) {
				return o.type;
			}
		}
		return null;
	}

	public String toString() {
		return type;
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
}

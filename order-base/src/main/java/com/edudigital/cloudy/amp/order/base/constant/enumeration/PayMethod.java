package com.edudigital.cloudy.amp.order.base.constant.enumeration;

public enum PayMethod {
	// 支付宝
	ALI(1, "pm_ali"),
	// 微信
	WX(2, "pm_wx"),
	// 银联
	CP(3, "pm_cp");

	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private PayMethod(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (PayMethod o : PayMethod.values()) {
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

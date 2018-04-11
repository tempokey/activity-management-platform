package com.edudigital.cloudy.amp.msg.base.constant.enumeration;

public enum MQExchange {

	TOPIC_EXCHANGE_NAME(1, "exc_ebk");

	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private MQExchange(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (MQExchange o : MQExchange.values()) {
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

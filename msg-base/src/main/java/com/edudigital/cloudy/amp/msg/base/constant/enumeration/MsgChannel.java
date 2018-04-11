package com.edudigital.cloudy.amp.msg.base.constant.enumeration;

public enum MsgChannel {
	// 支付宝
	ALI(1, "mc_ali"),
	// 其他方式
	REST(99, "mc_rest");

	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private MsgChannel(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (MsgChannel o : MsgChannel.values()) {
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

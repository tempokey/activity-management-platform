package com.edudigital.cloudy.amp.msg.base.constant.enumeration;

public enum MQQueue {

	TB_ORDERE_QUEUE_NAME(2, "q_tb"),

	TB_ORDER_ACK_QUEUE_NAME(3, "aq_tb"),

	TB_USER_REL_QUEUE_NAME(4, "user_rel_tb");

	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private MQQueue(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (MQQueue o : MQQueue.values()) {
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

package com.edudigital.cloudy.amp.msg.base.constant.enumeration;

public enum MsgMethod {
	// 注册
	TB_SIGNUP(1, "tb_sign_up"),
	// 登录
	TB_LOGIN(2, "tb_log_in"),
	// 找回密码
	TB_RECOVER(3, "tb_recover_password"),
	// 重置密码,
	TB_RESET(4, "tb_reset_password"),
	// 其他
	REST(99,"rest");

	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private MsgMethod(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (MsgMethod o : MsgMethod.values()) {
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

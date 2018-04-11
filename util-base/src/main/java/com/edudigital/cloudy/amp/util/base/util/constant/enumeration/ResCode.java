package com.edudigital.cloudy.amp.util.base.util.constant.enumeration;

/**
 * 返回状态码
 * 
 * @author Tempo
 * @date 2017年9月6日 上午10:16:01
 *
 */
public enum ResCode {
	// 成功
	SUCCESS(10000, "成功"),
	// 失败
	FAILURE(40000, "失败"),
	// 不存在
	FAILURE_INEXISTENCE(40001, "获取不存在"),
	// 数据库异常
	FAILURE_EDATABASE_ERROR(40002, "数据库异常"),
	// 无法处理的结果
	FAILURE_NO_CONDUCT(40003, "无法处理"),
	// 参数不匹配
	FAILURE_NOT_MATCHES(40004, "参数不匹配"),
	// 已登录
	HAS_LOGIN(40005, "已登录"),
	// 未登录
	NO_LOGIN(40006, "未登录"),
	// 移动端登陆时输入的账号密码有误
	TB_FAILURE_INEXISTENCE(40007, "手机号或密码有误，请核对并重新输入"),
	// 移动端账户已存在
	TB_FAILURE_EXISTENCE(40008, "该手机号已注册，请直接登录或更换手机号"),
	// 证码不正确
	TB_ERROR_MSGCODE(40009, "验证码不正确"),
	// 移动端账号
	TB_FAILURE_NOTEXISTENCE(40010, "该手机号尚未注册"),
	// 服务错误
	FAILURE_SERVICE(40011,"服务错误");
	// 成员变量
	private int index;
	private String type;

	// 构造方法
	private ResCode(int index, String type) {
		this.index = index;
		this.type = type;
	}

	// 普通方法
	public static String getType(int index) {
		for (ResCode o : ResCode.values()) {
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

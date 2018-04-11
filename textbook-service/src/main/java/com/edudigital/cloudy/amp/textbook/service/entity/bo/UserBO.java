package com.edudigital.cloudy.amp.textbook.service.entity.bo;

import java.util.List;

public class UserBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户Id
	private int userId;
	// 学校id
	private String schoolId;
	// 爱丁号
	private String adCode;
	// 名称
	private String name;
	// 账户
	private String account;
	// 密码
	private String password;
	// 出版社编码
	private int pressId;
	// 课程编码
	private int courseId;
	// 类型
	private int type;

	private List<RoleBO> roles;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPressId() {
		return pressId;
	}

	public void setPressId(int pressId) {
		this.pressId = pressId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<RoleBO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBO> roles) {
		this.roles = roles;
	}
}
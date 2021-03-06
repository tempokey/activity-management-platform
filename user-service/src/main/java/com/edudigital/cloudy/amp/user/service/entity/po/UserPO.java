package com.edudigital.cloudy.amp.user.service.entity.po;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Tempo
 * @date 2017年9月5日 下午3:29:45
 *
 */
public class UserPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户Id
	private int Id;
	// 用户唯一标识
	private String uuId;
	// 学校id
	private String schoolId;
	// 爱丁号
	private String adCode;
	// 名称
	private String name;
	// 手机号
	private String phone;
	// 账户
	private String account;
	// 随机生成码
	private String salt;
	// 密码
	private String password;
	// 出版社id
	private int pressId;
	// 课程id
	private int courseId;
	// 类型
	private int type;
	// 角色
	private List<RolePO> roles;
	//	用户ip
	private String remoteIp;
	//	当前tokenKey
	private String tokenKey;

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<RolePO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolePO> roles) {
		this.roles = roles;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Set<String> listRoleNames() {
		if (roles == null || roles.isEmpty()) {
			return null;
		}
		Set<String> set = new HashSet<String>();
		for (RolePO r : this.roles) {
			set.add(r.getRole());
		}
		return set;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

}

package com.edudigital.cloudy.amp.textbook.service.entity.po;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Tempo
 * @date 2017年7月21日 上午9:34:44
 *
 */
public class UserPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 用户Id
	private int Id;
	// 学校id
	private String schoolId;
	// 爱丁号
	private String adCode;
	// 名称
	private String name;
	// 账户
	private String account;
	// 随机生成码
	private String salt;
	// 密码
	private String password;
	// 出版社id
	private int pressId;
	// 课程id
	// 类型
	private int type;
	// 角色
	private List<RolePO> roles;

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

	public int getPressId() {
		return pressId;
	}

	public void setPressId(int pressId) {
		this.pressId = pressId;
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

	public Set<String> listRoleNames() {
		Set<String> set = new HashSet<String>();
		for (RolePO r : this.roles) {
			set.add(r.getRole());
		}
		return set;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
}
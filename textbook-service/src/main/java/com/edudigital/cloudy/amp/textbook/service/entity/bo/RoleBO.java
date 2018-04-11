package com.edudigital.cloudy.amp.textbook.service.entity.bo;

import java.util.List;

public class RoleBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int roleCode;

	private String role;

	private List<PermissionBO> permissions;

	public int getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(int roleCode) {
		this.roleCode = roleCode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<PermissionBO> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionBO> permissions) {
		this.permissions = permissions;
	}

}

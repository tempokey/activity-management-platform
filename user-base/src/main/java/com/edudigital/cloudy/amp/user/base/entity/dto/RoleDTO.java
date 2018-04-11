package com.edudigital.cloudy.amp.user.base.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int roleCode;

	private String role;

	private List<PermissionDTO> permissions;

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

	public List<PermissionDTO> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionDTO> permissions) {
		this.permissions = permissions;
	}

	public List<String> listPermissionNames() {
		if (permissions == null || permissions.isEmpty()) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		for (PermissionDTO p : permissions) {
			list.add(p.getPermission());
		}
		return list;
	}
}

package com.edudigital.cloudy.amp.textbook.service.entity.po;

import java.util.ArrayList;
import java.util.List;

public class RolePO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int roleCode;

	private String role;

	private List<PermissionPO> permissions;

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

	public List<PermissionPO> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionPO> permissions) {
		this.permissions = permissions;
	}

	public List<String> listPermissionNames() {
		List<String> list = new ArrayList<String>();
		for (PermissionPO p : permissions) {
			list.add(p.getPermission());
		}
		return list;
	}
}

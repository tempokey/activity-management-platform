package com.edudigital.cloudy.amp.user.service.entity.po;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Tempo
 * @date 2017年9月5日 下午3:22:54
 *
 */
public class RolePO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int roleId;

	private String role;

	private List<PermissionPO> permissions;

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
		if (permissions == null || permissions.isEmpty()) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		for (PermissionPO p : permissions) {
			list.add(p.getPermission());
		}
		return list;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}

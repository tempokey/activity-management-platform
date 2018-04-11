package com.edudigital.cloudy.amp.user.base.entity.dto;

import com.edudigital.cloudy.amp.user.base.entity.dto.BaseDTO;

public class PermissionDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int permissionCode;

	private String permission;

	public int getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(int permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}

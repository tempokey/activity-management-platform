package com.edudigital.cloudy.amp.user.service.entity.po;

/**
 * 
 * @author Tempo
 * @date 2017年9月5日 下午3:19:26
 *
 */
public class PermissionPO extends BasePO {
	//
	private static final long serialVersionUID = 1L;

	// 权限ID
	private int permissionId;

	// 权限
	private String permission;

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

}

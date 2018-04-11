package com.edudigital.cloudy.amp.user.service.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.edudigital.cloudy.amp.user.base.entity.dto.PermissionDTO;
import com.edudigital.cloudy.amp.user.base.entity.dto.ResDTO;
import com.edudigital.cloudy.amp.user.base.entity.dto.RoleDTO;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.user.service.entity.po.PermissionPO;
import com.edudigital.cloudy.amp.user.service.entity.po.RolePO;
import com.edudigital.cloudy.amp.user.service.entity.po.UserPO;
import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;

/**
 * 三层结构的权限对象copy
 * 
 * @author Tempo
 * @date 2017年7月30日 下午2:53:33
 *
 */
public class EntityUtils {

	/**
	 * copy list
	 * 
	 * @param source
	 *            原链表
	 * @param className
	 *            target 链表原子类
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static <T, E> List<E> exchange(List<T> source, String className)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		List<E> tarList = new ArrayList<>();
		if (source == null || source.isEmpty()) {
			throw new InstantiationException("source is null.");
		}
		for (T t : source) {
			E e = (E) Class.forName(className).newInstance();
			BeanUtils.copyProperties(t, e);
			tarList.add(e);
		}
		return tarList;
	}

	// 角色拷贝
	public static void copy(RolePO source, RoleDTO target) {
		BeanUtils.copyProperties(source, target);
		if (source.getPermissions() != null && !source.getPermissions().isEmpty()) {
			List<PermissionDTO> permissions = new ArrayList<>();
			for (PermissionPO permissionPO : source.getPermissions()) {
				PermissionDTO permissionDTO = new PermissionDTO();
				BeanUtils.copyProperties(permissionPO, permissionDTO);
				permissions.add(permissionDTO);
			}
			target.setPermissions(permissions);
		}
	}

	public static void copy(RoleDTO source, RolePO target) {
		BeanUtils.copyProperties(source, target);
		if (source.getPermissions() != null && !source.getPermissions().isEmpty()) {
			List<PermissionPO> permissions = new ArrayList<>();
			for (PermissionDTO permissionDTO : source.getPermissions()) {
				PermissionPO permissionPO = new PermissionPO();
				BeanUtils.copyProperties(permissionDTO, permissionPO);
				permissions.add(permissionPO);
			}
			target.setPermissions(permissions);
		}
	}

	// 用户拷贝
	public static void copy(UserDTO source, UserPO target) {
		BeanUtils.copyProperties(source, target);
		if (source.getRoles() != null && !source.getRoles().isEmpty()) {
			List<RolePO> roles = new ArrayList<>();
			for (RoleDTO roleDTO : source.getRoles()) {
				RolePO rolePO = new RolePO();
				copy(roleDTO, rolePO);
				roles.add(rolePO);
			}
			target.setRoles(roles);
		}
	}

	public static void copy(UserPO source, UserDTO target) {
		BeanUtils.copyProperties(source, target);
		if (source.getRoles() != null && !source.getRoles().isEmpty()) {
			List<RoleDTO> roles = new ArrayList<>();
			for (RolePO rolePO : source.getRoles()) {
				RoleDTO roleDTO = new RoleDTO();
				copy(rolePO, roleDTO);
				roles.add(roleDTO);
			}
			target.setRoles(roles);
		}
	}

	public static void pkg(ResDTO resDTO, ResCode resCode) {
		resDTO.setCode(resCode.getIndex());
		resDTO.setMsg(resCode.toString());
	}
}

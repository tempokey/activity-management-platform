package com.edudigital.cloudy.amp.textbook.service.util;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.AskDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PermissionDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.QuestionDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.RoleDTO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.AskBO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.PermissionBO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.QuestionBO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.RoleBO;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.UserBO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.AskPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.PermissionPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.QuestionPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.RolePO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.UserPO;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.util.base.util.BaseEntityUtils;

/**
 * 三层结构的权限对象copy
 * 
 * @author Tempo
 * @date 2017年7月30日 下午2:53:33
 *
 */
public class EntityUtils extends BaseEntityUtils {
	// 角色拷贝
	public static void copy(RolePO source, RoleDTO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getPermissions() != null && !source.getPermissions().isEmpty())
			target.setPermissions(exchange(source.getPermissions(), PermissionDTO.class.getName()));
	}

	public static void copy(RoleDTO source, RolePO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getPermissions() != null && !source.getPermissions().isEmpty())
			target.setPermissions(exchange(source.getPermissions(), PermissionPO.class.getName()));
	}

	public static void copy(RoleDTO source, RoleBO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getPermissions() != null && !source.getPermissions().isEmpty())
			target.setPermissions(exchange(source.getPermissions(), PermissionBO.class.getName()));
	}

	public static void copy(RoleBO source, RoleDTO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getPermissions() != null && !source.getPermissions().isEmpty())
			target.setPermissions(exchange(source.getPermissions(), PermissionDTO.class.getName()));
	}

	// 用户拷贝
	public static void copy(UserDTO source, UserPO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getRoles() != null && !source.getRoles().isEmpty())
			target.setRoles(exchange(source.getRoles(), RolePO.class.getName()));
	}

	public static void copy(UserPO source, UserDTO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getRoles() != null && !source.getRoles().isEmpty())
			target.setRoles(exchange(source.getRoles(), RoleDTO.class.getName()));
	}

	public static void copy(UserBO source, UserDTO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getRoles() != null && !source.getRoles().isEmpty())
			target.setRoles(exchange(source.getRoles(), RoleDTO.class.getName()));
	}

	public static void copy(UserDTO source, UserBO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getRoles() != null && !source.getRoles().isEmpty())
			target.setRoles(exchange(source.getRoles(), RoleBO.class.getName()));
	}

	public static void copy(QuestionBO source, QuestionDTO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getAsks() != null && !source.getAsks().isEmpty())
			target.setAsks(exchange(source.getAsks(), AskDTO.class.getName()));
	}

	public static void copy(QuestionDTO source, QuestionPO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getAsks() != null && !source.getAsks().isEmpty())
			target.setAsks(exchange(source.getAsks(), AskPO.class.getName()));
	}

	public static void copy(QuestionPO source, QuestionDTO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getAsks() != null && !source.getAsks().isEmpty())
			target.setAsks(exchange(source.getAsks(), AskDTO.class.getName()));
	}

	public static void copy(QuestionDTO source, QuestionBO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
		if (source.getAsks() != null && !source.getAsks().isEmpty())
			target.setAsks(exchange(source.getAsks(), AskBO.class.getName()));
	}
}

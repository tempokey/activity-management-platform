package com.edudigital.cloudy.amp.textbook.custom.util;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

import com.edudigital.cloudy.amp.textbook.base.entity.ao.UserAO;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.util.base.util.BaseEntityUtils;

public class EntityUtils extends BaseEntityUtils {
	// 用户拷贝
	public static void copy(UserAO source, UserDTO target)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		BeanUtils.copyProperties(source, target);
	}
}
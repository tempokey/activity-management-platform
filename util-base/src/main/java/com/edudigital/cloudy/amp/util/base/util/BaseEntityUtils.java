package com.edudigital.cloudy.amp.util.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;

import net.sf.json.JSONObject;

public abstract class BaseEntityUtils {
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

	public static <T> Map<String, String> instance2StrMap(T instance)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = instance.getClass().getDeclaredFields();
		if (null == fields || fields.length < 1) {
			return null;
		}
		for (Field field : fields) {
			field.setAccessible(true);
			String key = field.getName();
			map.put(key, field.get(instance).toString());
		}
		return map;
	}

	public static <T> Map<String, Object> instance2ObjMap(T instance)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = instance.getClass().getDeclaredFields();
		if (null == fields || fields.length < 1) {
			return null;
		}
		for (Field field : fields) {
			field.setAccessible(true);
			String key = field.getName();
			map.put(key, field.get(instance));
		}
		return map;
	}

	public static <T> Map<String, String> instanceNotNull2StrMap(T instance)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = instance.getClass().getDeclaredFields();
		if (null == fields || fields.length < 1) {
			return null;
		}
		for (Field field : fields) {
			field.setAccessible(true);
			String key = field.getName();
			Object val = field.get(instance);
			if (null != val) {
				map.put(key, val.toString());
			}
		}
		return map;
	}

	public static <T> Map<String, Object> instanceNotNull2ObjMap(T instance)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = instance.getClass().getDeclaredFields();
		if (null == fields || fields.length < 1) {
			return null;
		}
		for (Field field : fields) {
			field.setAccessible(true);
			String key = field.getName();
			Object val = field.get(instance);
			if (null != val) {
				map.put(key, val);
			}
		}
		return map;
	}

	public static <T> String entity2Str(T instance)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String str = null;
		Map<String, String> map = instanceNotNull2StrMap(instance);
		if (null == map) {
			return str;
		}
		JSONObject jsonMap = new JSONObject();
		jsonMap.putAll(map);
		JSONObject jsonObject = new JSONObject();
		String instantceStr = instance.getClass().getSimpleName().toString();
		jsonObject.put(instantceStr.replace(instantceStr.substring(0, 1), instantceStr.substring(0, 1).toLowerCase()),
				jsonMap.toString());
		str = jsonObject.toString();
		return str;
	}

	/**
	 * 封装返回码
	 * 
	 * @param resBO
	 * @param resCode
	 */
	public static void pkg(ResDTO resDTO, ResCode resCode) {
		resDTO.setCode(resCode.getIndex());
		resDTO.setMsg(resCode.toString());
	}
}

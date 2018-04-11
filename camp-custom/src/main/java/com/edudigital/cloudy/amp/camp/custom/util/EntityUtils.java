package com.edudigital.cloudy.amp.camp.custom.util;

import com.edudigital.cloudy.amp.util.base.util.BaseEntityUtils;
import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;

/**
 * 三层结构的权限对象copy
 * 
 * @author Tempo
 * @date 2017年7月30日 下午2:53:33
 *
 */
public class EntityUtils extends BaseEntityUtils {

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

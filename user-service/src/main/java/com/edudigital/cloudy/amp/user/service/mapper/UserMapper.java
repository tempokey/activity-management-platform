package com.edudigital.cloudy.amp.user.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.user.service.entity.po.UserPO;

/**
 * 
 * @author Tempo
 * @date 2017年9月5日 下午3:48:41
 *
 */
@Component
public interface UserMapper {

	/**
	 * 
	 * @param userPO
	 * @return
	 */
	public UserPO getUser(@Param("u") UserPO userPO);

	/**
	 * 
	 * @param userPO
	 * @return
	 */
	public List<UserPO> listUserPermission(@Param("u") UserPO userPO);

	/**
	 * 
	 * @param userPO
	 * @return
	 */
	public int addUser(@Param("u") UserPO userPO);

	/**
	 * 
	 * @param userPO
	 * @return
	 */
	public int updateUser(@Param("u") UserPO userPO);
}

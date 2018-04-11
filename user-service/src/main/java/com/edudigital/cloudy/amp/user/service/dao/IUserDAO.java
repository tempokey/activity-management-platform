package com.edudigital.cloudy.amp.user.service.dao;

import java.util.List;

import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

/**
 * 
 * @author Tempo
 * @date 2017年9月5日 下午3:55:42
 *
 */
public interface IUserDAO {
	/**
	 * 
	 * @param userPO
	 * @return
	 */
	public UserDTO getUser(UserDTO userDTO);

	/**
	 * 
	 * @param userDTO
	 * @return
	 */
	public UserDTO getUserPermission(UserDTO userDTO);

	/**
	 * 
	 * @param userPO
	 * @return
	 */
	public List<UserDTO> listUserPermission(UserDTO userDTO);

	/**
	 * 
	 * @param userPO
	 * @return
	 */
	public int addUser(UserDTO userDTO);

	/**
	 * 
	 * @param userDTO
	 * @return
	 */
	public int updateUser(UserDTO userDTO);
}

package com.edudigital.cloudy.amp.user.service.service;

import java.util.List;

import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

/**
 * 
 * @author Tempo
 * @date 2017年9月6日 下午4:18:44
 *
 */
public interface IUserService {
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
	 * @param userBO
	 * @return
	 */
	public int updateUser(UserDTO userDTO);
}

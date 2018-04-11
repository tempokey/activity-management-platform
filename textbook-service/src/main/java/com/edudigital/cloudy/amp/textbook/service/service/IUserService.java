package com.edudigital.cloudy.amp.textbook.service.service;

import java.util.List;

import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

/**
 * 
 * @author Tempo
 * @date 2017年7月21日 上午9:38:16
 *
 */
public interface IUserService {
	/**
	 * 
	 * @return
	 */
	public List<UserDTO> listUser();

	/**
	 * 
	 * @return
	 */
	public UserDTO getUser(UserDTO u);

	/**
	 * 
	 * @param name
	 * @return
	 */
	public UserDTO getUserByAccount(UserDTO u);

	/**
	 * 
	 * @param userDTO
	 * @return
	 */
	public int add(UserDTO userDTO);
}

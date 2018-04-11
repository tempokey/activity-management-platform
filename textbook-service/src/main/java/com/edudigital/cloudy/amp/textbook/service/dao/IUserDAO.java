package com.edudigital.cloudy.amp.textbook.service.dao;

import java.util.List;

import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

public interface IUserDAO {
	/**
	 * 
	 * @return
	 */
	public List<UserDTO> listUser();

	/**
	 * 
	 * @return
	 */
	public UserDTO getUser(UserDTO user);

	/**
	 * 
	 * @param name
	 * @return
	 */
	public UserDTO getUserByAccount(UserDTO user);

	/**
	 * 
	 * @param userDO
	 * @return
	 */
	public int add(UserDTO userDTO);
}

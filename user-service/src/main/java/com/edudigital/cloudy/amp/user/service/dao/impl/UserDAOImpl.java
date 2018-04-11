package com.edudigital.cloudy.amp.user.service.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.user.service.constant.enumeration.MethodType;
import com.edudigital.cloudy.amp.user.service.dao.IUserDAO;
import com.edudigital.cloudy.amp.user.service.entity.po.UserPO;
import com.edudigital.cloudy.amp.user.service.mapper.UserMapper;
import com.edudigital.cloudy.amp.user.service.util.EntityUtils;

@Service
public class UserDAOImpl implements IUserDAO {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDTO getUser(UserDTO userDTO) {
		logger.info(MethodType.DAO.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		UserPO userPO = new UserPO();
		UserDTO userDTO2 = new UserDTO();
		UserPO userPO2 = new UserPO();

		EntityUtils.copy(userDTO, userPO);

		userPO2 = userMapper.getUser(userPO);

		try {
			EntityUtils.copy(userPO2, userDTO2);
		} catch (Exception e) {
			return new UserDTO();
		}

		return userDTO2;
	}

	@Override
	public int addUser(UserDTO userDTO) {
		logger.info(MethodType.DAO.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		UserPO userPO = new UserPO();

		EntityUtils.copy(userDTO, userPO);

		return userMapper.addUser(userPO);
	}

	@Override
	public List<UserDTO> listUserPermission(UserDTO userDTO) {
		logger.info(MethodType.DAO.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		UserPO userPO = new UserPO();
		List<UserDTO> userDTOs = null;

		EntityUtils.copy(userDTO, userPO);

		List<UserPO> userPOs = userMapper.listUserPermission(userPO);

		try {
			Assert.notEmpty(userPOs, "Collection Is Empty!");

			userDTOs = EntityUtils.exchange(userPOs, UserDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return userDTOs;
	}

	@Override
	public UserDTO getUserPermission(UserDTO userDTO) {
		logger.info(MethodType.DAO.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		List<UserDTO> userDTOs = listUserPermission(userDTO);

		try {
			Assert.notEmpty(userDTOs, "Collection Is Empty!");
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			return null;
		}

		return userDTOs.get(0);
	}

	@Override
	public int updateUser(UserDTO userDTO) {
		logger.info(MethodType.DAO.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		UserPO userPO = new UserPO();

		EntityUtils.copy(userDTO, userPO);

		return userMapper.updateUser(userPO);
	}

}

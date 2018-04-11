package com.edudigital.cloudy.amp.user.service.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.user.service.dao.IUserDAO;
import com.edudigital.cloudy.amp.user.service.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserDAO userDao;

	@Override
	public UserDTO getUser(UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return userDao.getUser(userDTO);
	}

	@Override
	public UserDTO getUserPermission(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> listUserPermission(UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return userDao.listUserPermission(userDTO);
	}

	@Override
	public int addUser(UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return userDao.addUser(userDTO);
	}

	@Override
	public int updateUser(UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return userDao.updateUser(userDTO);
	}

}

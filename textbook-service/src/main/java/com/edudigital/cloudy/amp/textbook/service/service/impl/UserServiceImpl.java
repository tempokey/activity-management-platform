package com.edudigital.cloudy.amp.textbook.service.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.textbook.service.dao.IUserDAO;
import com.edudigital.cloudy.amp.textbook.service.service.IUserService;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

@Service
public class UserServiceImpl implements IUserService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserDAO userDAO;

	@Override
	public List<UserDTO> listUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUser(UserDTO user) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return userDAO.getUser(user);
	}

	@Override
	public UserDTO getUserByAccount(UserDTO user) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return userDAO.getUserByAccount(user);
	}

	@Override
	public int add(UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return userDAO.add(userDTO);
	}

}

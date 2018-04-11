package com.edudigital.cloudy.amp.textbook.service.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.edudigital.cloudy.amp.textbook.service.constant.AssertMessages;
import com.edudigital.cloudy.amp.textbook.service.dao.IUserDAO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.UserPO;
import com.edudigital.cloudy.amp.textbook.service.mapper.UserMapper;
import com.edudigital.cloudy.amp.textbook.service.util.EntityUtils;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

@Component
public class UserDAO implements IUserDAO {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDTO> listUser() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return null;
	}

	@Override
	public UserDTO getUser(UserDTO user) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		UserPO userDO = new UserPO();
		UserDTO userDTO = new UserDTO();

		try {
			EntityUtils.copy(user, userDO);

			userDO = userMapper.getUser(userDO);

			EntityUtils.copy(userDO, userDTO);
		} catch (Exception e) {
			return null;
		}

		return userDTO;
	}

	@Override
	public UserDTO getUserByAccount(UserDTO user) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		UserPO userDO = new UserPO();
		UserDTO userDTO = new UserDTO();

		userDO.setAccount(user.getAccount());
		List<UserPO> userList = userMapper.getUserByAccount(userDO);

		try {
			Assert.notEmpty(userList, AssertMessages.AM_COLLECTION_IS_EMPTY);
			userDO = userList.get(0);

			EntityUtils.copy(userDO, userDTO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

		return userDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public int add(UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		UserPO userDO = new UserPO();

		try {
			EntityUtils.copy(userDTO, userDO);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
			return -1;
		}

		return userMapper.add(userDO);
	}
}

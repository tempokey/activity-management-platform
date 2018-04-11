package com.edudigital.cloudy.amp.textbook.custom.service.fallback;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.textbook.custom.service.IUserService;
import com.edudigital.cloudy.amp.textbook.custom.util.EntityUtils;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;

@Component
public class UserServiceFallBack implements IUserService {

	@Override
	public void addUser(UserDTO userDTO) {

	}

	@Override
	public ResDTO signUp(UserDTO userDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO logIn(UserDTO userDTO, String remoteIp) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO updateUser(UserDTO userDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public UserDTO getUser(Map<String, Object> map) {
		return null;
	}

	@Override
	public ResDTO checkLoginStatus(Map<String, Object> map) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO logout(UserDTO userDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

}

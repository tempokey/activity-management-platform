package com.edudigital.cloudy.amp.textbook.custom.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edudigital.cloudy.amp.textbook.custom.service.fallback.UserServiceFallBack;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;

@FeignClient(name = "user-service", fallback = UserServiceFallBack.class)
public interface IUserService {

	@RequestMapping(value = "/user-service/user/addUser", method = { RequestMethod.POST })
	public void addUser(@RequestBody UserDTO userDTO);

	@RequestMapping(value = "/user-service/user/signup", method = { RequestMethod.POST })
	public ResDTO signUp(@RequestBody UserDTO userDTO);

	@RequestMapping(value = "/user-service/user/login", method = { RequestMethod.PUT })
	public ResDTO logIn(@RequestBody UserDTO userDTO, @RequestParam("remoteIp") String remoteIp);

	@RequestMapping(value = "/user-service/user/updateUser", method = { RequestMethod.PUT })
	public ResDTO updateUser(@RequestBody UserDTO userDTO);

	@RequestMapping(value = "/user-service/user/getUser", method = { RequestMethod.GET })
	public UserDTO getUser(@RequestParam Map<String, Object> map);

	@RequestMapping(value = "/user-service/user/checkLoginStatus", method = { RequestMethod.GET })
	public ResDTO checkLoginStatus(@RequestParam Map<String, Object> map);

	@RequestMapping(value = "/user-service/user/logout", method = { RequestMethod.PUT })
	public ResDTO logout(@RequestBody UserDTO userDTO);

}

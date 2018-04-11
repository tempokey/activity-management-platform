package com.edudigital.cloudy.amp.user.service.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.user.base.entity.dto.ResDTO;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.user.service.auth.Auth;
import com.edudigital.cloudy.amp.user.service.constant.enumeration.MethodType;
import com.edudigital.cloudy.amp.user.service.service.IUserService;
import com.edudigital.cloudy.amp.user.service.util.EntityUtils;
import com.edudigital.cloudy.amp.user.service.util.SignUtils;
import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;
import com.edudigital.cloudy.amp.util.base.util.res.ResUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@CrossOrigin(origins = "*", allowCredentials = "true")
@SuppressWarnings("static-access")
@RestController
@RequestMapping(value = "/user/")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	@Autowired
	private Auth auth;

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public ResDTO signUp(HttpServletRequest req, HttpServletResponse res, @RequestBody UserDTO userDTO) {
		logger.info(MethodType.HANDLER.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		userDTO.setPassword(SignUtils.signSHA1(userDTO.getPassword()));
		userDTO.setUuId(UUID.randomUUID().toString().replace("-", ""));
		ResCode resCode = userService.addUser(userDTO) > 0 ? ResCode.SUCCESS : ResCode.FAILURE;
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, resCode);
		if (resDTO.getCode() == 10000) {
			UserDTO user = userService.getUser(userDTO);
			JSONArray jsonArray = new JSONArray();
			jsonArray.add(user);
			resDTO.setData(jsonArray.toString());
		}
		return resDTO;
	}

	@Transactional
	@RequestMapping(value = "login", method = RequestMethod.PUT)
	public ResDTO logIn(HttpServletRequest req, HttpServletResponse res, @RequestBody UserDTO userDTO,
			@RequestParam("remoteIp") String remoteIp) {
		logger.info(MethodType.HANDLER.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		String username = userDTO.getAccount();
		String password = SignUtils.signSHA1(userDTO.getPassword());
		userDTO.setPassword(password);
		String tokenKey = UUID.randomUUID().toString().replace("-", "");

		ResDTO resDTO = new ResDTO();

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 根据用户名和密码进行用户查询
		UserDTO user = userService.getUser(userDTO);
		if (null == user.getAccount()) {
			logger.info("用户[" + username + "]登录认证未通过");
			token.clear();
			EntityUtils.pkg(resDTO, ResCode.FAILURE_INEXISTENCE);
			return resDTO;
		}
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + username + "]进行登录验证..验证开始");
			auth.login(tokenKey, token);
			logger.info("对用户[" + username + "]进行登录验证..验证通过");
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
		}
		// 验证是否登录成功
		if (auth.getSubjecyt(tokenKey).isAuthenticated()) {

			logger.info("用户[" + username + "]登录认证通过");

			user.setTokenKey(tokenKey);
			user.setRemoteIp(remoteIp);
			userService.updateUser(user);
			EntityUtils.pkg(resDTO, ResCode.SUCCESS);
			JSONArray jsonArray = new JSONArray();
			jsonArray.add(user);
			resDTO.setData(jsonArray.toString());
		}
		return resDTO;
	}

	@RequestMapping(value = "signin", method = RequestMethod.GET)
	public void signIn(HttpServletRequest req, HttpServletResponse res) {
		logger.info(MethodType.HANDLER.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();

		ResCode resCode = (currentUser != null && currentUser.isAuthenticated()) ? ResCode.SUCCESS : ResCode.FAILURE;
		logger.info("ResCode:" + resCode.toString());

		ResDTO resBO = new ResDTO();
		EntityUtils.pkg(resBO, resCode);
		ResUtils.toJson(res, new JSONObject().fromObject(resBO));
	}

	@RequestMapping(value = "logout", method = RequestMethod.PUT)
	public ResDTO logOut(HttpServletRequest req, HttpServletResponse res, @RequestBody UserDTO userDTO) {
		logger.info(MethodType.HANDLER.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());
		// 获取当前的Subject
		String sessionID = req.getHeader(auth.getSHIRO_SESSION_KEY());
		ResDTO resDTO = new ResDTO();

		auth.logout(sessionID);

		EntityUtils.pkg(resDTO, ResCode.SUCCESS);
		return resDTO;
	}

	@RequestMapping(value = "getUser", method = { RequestMethod.GET })
	public UserDTO getUser(HttpServletRequest req, HttpServletResponse res, UserDTO userDTO) {

		logger.info(MethodType.HANDLER.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		UserDTO user = userService.getUser(userDTO);

		return user;
	}

	@RequestMapping(value = "updateUser", method = { RequestMethod.PUT })
	public ResDTO updateUser(HttpServletRequest req, HttpServletResponse res, @RequestBody UserDTO userDTO) {
		logger.info(MethodType.HANDLER.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		userDTO.setPassword(SignUtils.signSHA1(userDTO.getPassword()));
		int result = userService.updateUser(userDTO);

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, result > 0 ? ResCode.SUCCESS : ResCode.FAILURE);
		resDTO.setData(new Integer(result).toString());

		return resDTO;
	}

	@RequestMapping(value = "addUser", method = { RequestMethod.POST })
	public int addUser(HttpServletRequest req, HttpServletResponse res, @RequestBody UserDTO userDTO) {

		logger.info(MethodType.HANDLER.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());

		int result = userService.addUser(userDTO);

		return result;
	}

	@RequestMapping(value = "checkLoginStatus", method = { RequestMethod.GET })
	public ResDTO checkLoginStatus(HttpServletRequest req, HttpServletResponse res, UserDTO userDTO) {
		logger.info(MethodType.HANDLER.toString() + Thread.currentThread().getStackTrace()[1].getMethodName());
		// 获取当前用户的tokenyKey 进行登陆状态判断
		String tokenKey = userDTO.getTokenKey();
		Subject subject = auth.getSubjecyt(tokenKey);
		ResDTO resDTO = new ResDTO();
		if (subject != null) {
			auth.putSubject(tokenKey, subject);
			logger.info("用户[" + userDTO.getName() + "]已登录");
			EntityUtils.pkg(resDTO, ResCode.HAS_LOGIN);
			return resDTO;
		} else {
			logger.info("用户[" + userDTO.getName() + "]未登录");
			EntityUtils.pkg(resDTO, ResCode.NO_LOGIN);
			return resDTO;
		}
	}

}

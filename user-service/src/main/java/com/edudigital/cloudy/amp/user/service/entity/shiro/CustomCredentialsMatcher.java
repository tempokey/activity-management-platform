package com.edudigital.cloudy.amp.user.service.entity.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 *
 * 验证密码是否正确自定义加密方法 Created by Administrator on 2017/5/22.
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken uToken = (UsernamePasswordToken) token;

		Object accountCredentials = getCredentials(info);
		try {
			String tokenPassword = new String(uToken.getPassword());
			String logInPassword = accountCredentials.toString();
			if (StringUtils.isBlank(tokenPassword) || StringUtils.isBlank(logInPassword)) {
				return false;
			} else {
				return tokenPassword == null ?false:tokenPassword.equals(logInPassword);
				//return SignUtils.signSHA1(tokenPassword).equals(SignUtils.signSHA1(logInPassword));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
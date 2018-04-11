package com.edudigital.cloudy.amp.user.service.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edudigital.cloudy.amp.user.service.auth.Auth;

public class ShiroUserFilter extends UserFilter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Auth auth;

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		logger.info("拦截User用户");
		logger.info("封装HttpServlet");
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		HttpServletResponse httpResponse = WebUtils.toHttp(response);

		logger.info("Http 第一次我手协议 放过，并设置过滤");
		if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			httpResponse.setHeader("Access-control-Allow-Origin", "*");
			httpResponse.setHeader("Access-Control-Allow-Methods", "POST,DELETE,PUT,GET,OPTIONS");
			httpResponse.setHeader("Access-Control-Allow-Headers",
					httpRequest.getHeader("Access-Control-Request-Headers"));
			httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpResponse.setStatus(HttpStatus.OK.value());
			return false;
		}

		logger.info("放过正常请求");
		return super.preHandle(request, response);
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		logger.info("放过登陆请求");
		if (isLoginRequest(request, response)) {
			return true;
		} else {
			logger.info("非登陆请求的 user权限校验");
			HttpServletRequest httpRequest = WebUtils.toHttp(request);

			logger.info("获取封装的 subject");
			Subject subject = auth.getSubjecyt(httpRequest.getHeader(auth.getSHIRO_SESSION_KEY()));

			return subject.getPrincipal() != null;
		}
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);

		Subject tmp = auth.getSubjecyt(httpRequest.getHeader(auth.getSHIRO_SESSION_KEY()));
		if (tmp == null) {
			saveRequestAndRedirectToLogin(request, response);
			return false;
		} else {
			return true;
		}
	}

}

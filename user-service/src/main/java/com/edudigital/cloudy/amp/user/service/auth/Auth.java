package com.edudigital.cloudy.amp.user.service.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.user.service.cache.redis.IRedisClt;

@Component
public class Auth {
	private String SHIRO_SESSION_KEY = "Subject-Session-ID";

	private Long EXPIRE_TIME = 900l;

	@Autowired
	private IRedisClt redisClt;

	// private static Map<String, Subject> subjectMap = new ConcurrentHashMap<>();

	public String getSHIRO_SESSION_KEY() {
		return SHIRO_SESSION_KEY;
	}

	public void putSubject(String key, Subject subject) {
		redisClt.save(key, subject, this.EXPIRE_TIME);
	}

	public Subject getSubjecyt(String key) {
		Subject subject = (Subject) redisClt.get(key);
		return subject == null ? SecurityUtils.getSubject() : subject;
	}

	public void removeSubject(String key) {
		redisClt.remove(key);
	}

	public void login(String key, UsernamePasswordToken token) {
		// 获取当前的Subject
		Subject subject = SecurityUtils.getSubject();

		if (subject != null && subject.isAuthenticated()) {
			SecurityUtils.getSubject().logout();
		}

		subject.login(token);
		putSubject(key, subject);
	}

	public void logout(String key) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null && subject.isAuthenticated()) {
			SecurityUtils.getSubject().logout();
		} else {
			removeSubject(key);
		}
	}
}

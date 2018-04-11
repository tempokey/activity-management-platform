package com.edudigital.cloudy.amp.user.service.cache.redis;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisClt implements IRedisClt {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, Object> valueOperations;

	private final Long EXPIRE_TIME = 900l;

	private final Long STEP = 1l;

	@Override
	public void save(String key, Object value, Long timeout) {
		valueOperations.set(key, value, timeout, TimeUnit.SECONDS);
	}

	@Override
	public void save(String key, Object value) {
		save(key, value, this.EXPIRE_TIME);
	}

	@Override
	public Object put(String key, Object value) {
		return valueOperations.getAndSet(key, value);
	}

	@Override
	public Object get(String key) {
		return valueOperations.get(key);
	}

	@Override
	public List<Object> list(Collection<String> keys) {
		return valueOperations.multiGet(keys);
	}

	@Override
	public void remove(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public void removes(Collection<String> keys) {
		redisTemplate.delete(keys);
	}

	public void increment(String key) {
		valueOperations.increment(key, this.STEP);
	}

	public void increment(String key, Long step) {
		valueOperations.increment(key, step);
	}

}

package com.edudigital.cloudy.amp.msg.service.service.redis;

import java.util.Collection;
import java.util.List;

/**
 * redis client
 * 
 * @author Tempo
 * @date 2018年1月16日 上午9:17:37
 *
 */
public interface IRedisClt {

	public void save(String key, Object value);

	public void save(String key, Object value, Long timeout);

	public void remove(String key);

	public void removes(Collection<String> keys);

	public Object put(String key, Object value);

	public Object get(String key);

	public List<Object> list(Collection<String> keys);

	public void increment(String key);

	public void increment(String key, Long step);

}

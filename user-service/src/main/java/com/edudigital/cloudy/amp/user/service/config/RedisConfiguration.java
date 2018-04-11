package com.edudigital.cloudy.amp.user.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.edudigital.cloudy.amp.user.service.entity.RedisObjectSerializer;

@Configuration
public class RedisConfiguration {

	// @Bean
	// public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory
	// redisConnectionFactory) {
	// Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new
	// Jackson2JsonRedisSerializer<Object>(
	// Object.class);
	// ObjectMapper om = new ObjectMapper();
	// om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	// om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	// jackson2JsonRedisSerializer.setObjectMapper(om);
	// RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	// template.setConnectionFactory(redisConnectionFactory);
	// template.setKeySerializer(jackson2JsonRedisSerializer);
	// template.setValueSerializer(jackson2JsonRedisSerializer);
	// template.setHashKeySerializer(jackson2JsonRedisSerializer);
	// template.setHashValueSerializer(jackson2JsonRedisSerializer);
	// template.afterPropertiesSet();
	// return template;
	// }

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}

}

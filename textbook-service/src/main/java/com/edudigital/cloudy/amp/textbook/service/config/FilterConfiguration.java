package com.edudigital.cloudy.amp.textbook.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.edudigital.cloudy.amp.textbook.service.filter.CorsFilter;

@Configuration
public class FilterConfiguration implements WebMvcConfigurer {

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CorsFilter()).addPathPatterns("/**");
	}
}

package com.edudigital.cloudy.amp.textbook.custom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
		registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/image/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
		registry.addResourceHandler("/plugin/**").addResourceLocations("/WEB-INF/plugin/");
		registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
		registry.addResourceHandler("/tld/**").addResourceLocations("/WEB-INF/tld/");
		registry.addResourceHandler("/taglib/**").addResourceLocations("/WEB-INF/jsp/");
	}

}
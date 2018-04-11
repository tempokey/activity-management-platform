package com.edudigital.cloudy.amp.camp.custom.config;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.edudigital.cloudy.amp.camp.custom.filter.CorsFilter;

@Configuration
public class FilterConfiguration implements WebMvcConfigurer {

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CorsFilter()).addPathPatterns("/**");
	}

	private static final Logger logger = LoggerFactory.getLogger(FilterConfiguration.class);

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		String singleFileSize = "500MB";
		String allFilesSize = "2500MB";
		MultipartConfigFactory factory = new MultipartConfigFactory();
		logger.info("单一文件上传大小" + singleFileSize);
		// 设置文件大小限制 ,超出设置页面会抛出异常信息，文件最大
		factory.setMaxFileSize(singleFileSize); // KB,MB
		logger.info("文件上传总大小");
		/// 设置总上传数据总大小
		factory.setMaxRequestSize(allFilesSize);
		return factory.createMultipartConfig();
	}
}

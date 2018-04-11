package com.edudigital.cloudy.amp.textbook.service.service;

import org.springframework.beans.factory.annotation.Value;

public class BaseYamlValues {

	@Value("${file.path}")
	protected String filePath;
}

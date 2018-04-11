package com.edudigital.cloudy.amp.file.service.service;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseYamlValues {

	@Value("${tb.static.file.dir}")
	protected String BASE_PATH;

}

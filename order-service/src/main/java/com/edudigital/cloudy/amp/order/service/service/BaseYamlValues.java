package com.edudigital.cloudy.amp.order.service.service;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseYamlValues {
	@Value("${amp.pay.wx.notify_url}")
	protected String notify_url;

	@Value("${amp.pay.ali.notify_url}")
	protected String ali_notify_url;

	@Value("${amp.pay.wx.notify_url}")
	protected String wx_notify_url;
}

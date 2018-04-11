package com.edudigital.cloudy.amp.msg.service.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MsgConstant {
	@Value("${amp.msg.ali.product}")
	public String ALI_PRODUCT;
	@Value("${amp.msg.ali.domain}")
	public String ALI_DOMAIN;
	@Value("${amp.msg.ali.region}")
	public String ALI_REGION;
	@Value("${amp.msg.ali.accesskeyid}")
	public String ALI_ACCESS_KEY_ID;
	@Value("${amp.msg.ali.accesskeysecret}")
	public String ALI_ACCESS_KEY_SECRET;
}

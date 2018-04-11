package com.edudigital.cloudy.amp.order.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.edudigital.cloudy.dc.pay.core.PayClient;
import com.edudigital.cloudy.dc.pay.core.PayConfig;

@Configuration
public class CommonConfiguration {

	@Value("${amp.pay.wx.appid}")
	private String wxAppID;

	@Value("${amp.pay.wx.mchid}")
	private String wxMchID;

	@Bean
	public boolean initConfig() {
		//初始化微信配置
		PayConfig payConfig = new PayConfig();
		payConfig.setWxAppId(wxAppID);
		payConfig.setWxMchId(wxMchID);
		PayClient.getInstance(payConfig);
		return true;
	}

}

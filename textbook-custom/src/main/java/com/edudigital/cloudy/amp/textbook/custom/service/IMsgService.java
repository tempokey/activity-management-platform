package com.edudigital.cloudy.amp.textbook.custom.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edudigital.cloudy.amp.msg.base.entity.MsgDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.fallback.MsgServiceFallBack;

@FeignClient(name = "msg-service",fallback = MsgServiceFallBack.class)
public interface IMsgService {

	@RequestMapping(value = "/msg-service/msg/sendMsg", method = { RequestMethod.POST })
	public MsgDTO sendMsg(@RequestBody MsgDTO msgDTO);

	@RequestMapping(value = "/msg-service/msg/verifyMsg", method = { RequestMethod.GET })
	public boolean verifyMsg(@RequestParam("msgID") String msgID, @RequestParam("code") String code);

}

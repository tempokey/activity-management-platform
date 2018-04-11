package com.edudigital.cloudy.amp.msg.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MsgStatus;
import com.edudigital.cloudy.amp.msg.base.entity.MsgDTO;
import com.edudigital.cloudy.amp.msg.service.service.msg.IMsgService;

@RestController
@RequestMapping(value = "/msg")
public class MsgController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3468910607846863483L;

	@Autowired
	private IMsgService msgService;

	@RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
	public MsgDTO sendMsg(HttpServletRequest req, HttpServletResponse res, @RequestBody MsgDTO msgDTO) {
		if (null != msgDTO) {
			msgDTO.setMsgStatus(MsgStatus.DEFEAT);
		}
		if (null == msgDTO || StringUtils.isBlank(msgDTO.getMsgID()) || StringUtils.isBlank(msgDTO.getPhones())
				|| null == msgDTO.getMsgMethod() || StringUtils.isBlank(msgDTO.getMsgMethod().toString())) {
			return msgDTO;
		}
		return msgService.sendMsg(msgDTO);
	}

	@RequestMapping(value = "/verifyMsg", method = RequestMethod.GET)
	public boolean verifyMsg(@RequestParam("msgID") String msgID, @RequestParam("code") String code) {
		if (StringUtils.isBlank(msgID) || StringUtils.isBlank(code)) {
			return false;
		}
		return msgService.verifyMsg(msgID, code);
	}

}

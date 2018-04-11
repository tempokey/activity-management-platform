package com.edudigital.cloudy.amp.textbook.custom.service.fallback;

import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MsgStatus;
import com.edudigital.cloudy.amp.msg.base.entity.MsgDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.IMsgService;
@Component
public class MsgServiceFallBack implements IMsgService{

	@Override
	public MsgDTO sendMsg(MsgDTO msgDTO) {
		msgDTO.setMsgStatus(MsgStatus.DEFEAT);
		return msgDTO;
	}

	@Override
	public boolean verifyMsg(String msgID, String code) {
		return false;
	}

}

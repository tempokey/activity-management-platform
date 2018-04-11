package com.edudigital.cloudy.amp.msg.service.service.msg;

import com.edudigital.cloudy.amp.msg.base.entity.MsgDTO;

public interface IMsgService {

	/**
	 * 发送简讯
	 * 
	 * @param msgDTO
	 * @return
	 */
	MsgDTO sendMsg(MsgDTO msgDTO);

	/**
	 * 检验简讯
	 * 
	 * @param msgID
	 * @param code
	 * @return
	 */
	boolean verifyMsg(String msgID, String code);

}

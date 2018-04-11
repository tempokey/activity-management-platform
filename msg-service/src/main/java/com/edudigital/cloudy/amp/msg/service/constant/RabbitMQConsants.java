package com.edudigital.cloudy.amp.msg.service.constant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.edudigital.cloudy.amp.msg.service.entity.po.RabbitMqMsgPO;
import com.edudigital.cloudy.amp.msg.service.mapper.MsgMapper;

public class RabbitMQConsants {

	public final static String TOPIC_EXCHANGE_NAME = "exc_ebk";

	public final static String QUEUE_NAME = "q_ebk";

	public final static String ACK_QUEUE_NAME = "aq_ebk";

	@Autowired
	private MsgMapper msgMapper;

	public List<RabbitMqMsgPO> getMqConfig() {

		List<RabbitMqMsgPO> config = new ArrayList<>();
		config = msgMapper.getRabbitMqConfig();
		return config;
	}

}

package com.edudigital.cloudy.amp.order.service.service;

import com.edudigital.cloudy.amp.textbook.base.entity.ao.RabbitMqMsgAO;

public interface IRabbitMQSendService {

	public void sendQueue(RabbitMqMsgAO msg);

	public void sendAckQueue(RabbitMqMsgAO msg);
	
	public void sendGetUserQueue(RabbitMqMsgAO msg);
	
//	public void sendXxQueue(RabbitMqMsgAO msg);
}

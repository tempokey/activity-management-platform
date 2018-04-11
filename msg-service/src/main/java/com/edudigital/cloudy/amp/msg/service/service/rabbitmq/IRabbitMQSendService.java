package com.edudigital.cloudy.amp.msg.service.service.rabbitmq;

import com.edudigital.cloudy.amp.msg.service.entity.po.RabbitMqMsgPO;

public interface IRabbitMQSendService {

	public void sendQueue(RabbitMqMsgPO msg);

	public void sendAckQueue(RabbitMqMsgPO msg);
}

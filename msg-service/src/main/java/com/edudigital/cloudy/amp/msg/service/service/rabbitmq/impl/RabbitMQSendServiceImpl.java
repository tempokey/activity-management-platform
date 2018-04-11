package com.edudigital.cloudy.amp.msg.service.service.rabbitmq.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.msg.service.constant.RabbitMQConsants;
import com.edudigital.cloudy.amp.msg.service.entity.po.RabbitMqMsgPO;
import com.edudigital.cloudy.amp.msg.service.service.BaseService;
import com.edudigital.cloudy.amp.msg.service.service.rabbitmq.IRabbitMQSendService;

@Service
public class RabbitMQSendServiceImpl extends BaseService
		implements RabbitTemplate.ConfirmCallback, IRabbitMQSendService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -787368249359721802L;

	@Autowired
	public RabbitMQSendServiceImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setConfirmCallback(this);
	}

	private RabbitTemplate rabbitTemplate;

	@Override
	public void sendQueue(RabbitMqMsgPO msg) {
//		this.rabbitMessagingTemplate.convertAndSend(RabbitMQConsants.TOPIC_EXCHANGE_NAME, RabbitMQConsants.QUEUE_NAME,
//				msg);
		logger.info(RabbitMQConsants.TOPIC_EXCHANGE_NAME + "-" + RabbitMQConsants.QUEUE_NAME + "-" + msg.getMsg());
	}

	@Override
	public void sendAckQueue(RabbitMqMsgPO msg) {
		// this.rabbitMessagingTemplate.convertAndSend(RabbitMQConsants.TOPIC_EXCHANGE_NAME,
		// RabbitMQConsants.ACK_QUEUE_NAME, msg);
		logger.info(RabbitMQConsants.TOPIC_EXCHANGE_NAME + "-" + RabbitMQConsants.ACK_QUEUE_NAME + "-" + msg.getMsg());
	}

	@Override
	public void confirm(CorrelationData arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub

	}
}

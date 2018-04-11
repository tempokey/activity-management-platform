package com.edudigital.cloudy.amp.order.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.msg.base.constant.MQQueueConstant;
import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MQExchange;
import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MQQueue;
import com.edudigital.cloudy.amp.order.service.service.IRabbitMQSendService;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.RabbitMqMsgAO;

@Service
public class RabbitMQSendServiceImpl implements IRabbitMQSendService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;

	@Override
	public void sendQueue(RabbitMqMsgAO msg) {
		this.rabbitMessagingTemplate.convertAndSend(MQExchange.TOPIC_EXCHANGE_NAME.toString(),
				MQQueue.TB_ORDERE_QUEUE_NAME.toString(), msg);
		logger.info(MQExchange.TOPIC_EXCHANGE_NAME.toString() + "-" + MQQueue.TB_ORDERE_QUEUE_NAME.toString() + "-"
				+ msg.getMsg());
	}

	@Override
	public void sendAckQueue(RabbitMqMsgAO msg) {
		this.rabbitMessagingTemplate.convertAndSend(MQExchange.TOPIC_EXCHANGE_NAME.toString(),
				MQQueue.TB_ORDER_ACK_QUEUE_NAME.toString(), msg);
		logger.info(MQExchange.TOPIC_EXCHANGE_NAME.toString() + "-" + MQQueue.TB_ORDER_ACK_QUEUE_NAME.toString() + "-"
				+ msg.getMsg());
	}

	@Override
	public void sendGetUserQueue(RabbitMqMsgAO msg) {
		this.rabbitMessagingTemplate.convertAndSend(MQExchange.TOPIC_EXCHANGE_NAME.toString(),
				MQQueueConstant.TB_USER_REL_QUEUE_NAME, msg);
		logger.info(MQExchange.TOPIC_EXCHANGE_NAME.toString() + "-" + MQQueue.TB_USER_REL_QUEUE_NAME.toString() + "-"
				+ msg.getMsg());

	}

//	@Override
//	public void sendXxQueue(RabbitMqMsgAO msg) {
//		this.rabbitMessagingTemplate.convertAndSend(MQExchange.TOPIC_EXCHANGE_NAME.toString(),
//				MQQueueConstant.TB_TEST_QUEUE_NAME, msg);
//		logger.info(MQExchange.TOPIC_EXCHANGE_NAME.toString() + "-" + MQQueue.TB_TEST_QUEUE_NAME.toString() + "-"
//				+ msg.getMsg());
//		
//	}
}

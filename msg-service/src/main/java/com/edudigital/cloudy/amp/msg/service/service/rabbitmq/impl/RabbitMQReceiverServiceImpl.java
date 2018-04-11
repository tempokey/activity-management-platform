package com.edudigital.cloudy.amp.msg.service.service.rabbitmq.impl;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.msg.service.constant.RabbitMQConsants;
import com.edudigital.cloudy.amp.msg.service.service.BaseService;
import com.edudigital.cloudy.amp.msg.service.service.rabbitmq.IRabbitMQReceiverService;

@Service
public class RabbitMQReceiverServiceImpl extends BaseService implements IRabbitMQReceiverService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1219705727065507077L;

	// @RabbitListener(queues = RabbitMQConsants.QUEUE_NAME)
	public void receiveQueue(Message message, com.rabbitmq.client.Channel channel) throws IOException {
		// logger.info(RabbitMQConsants.QUEUE_NAME + msg.getMsg() + "--Time--" + new
		// Date(msg.getTimestamp()));
		byte[] body = message.getBody();
		Long deliveryTag = message.getMessageProperties().getDeliveryTag();

		String string = new String(body);
		logger.info(message.getMessageProperties().getConsumerQueue() + ":" + string);

		// JSONObject jsonObject = JSONObject.fromObject(string);
		// RabbitMqMsgAO rabbitMqMsgAO = (RabbitMqMsgAO) JSONObject.toBean(jsonObject,
		// RabbitMqMsgAO.class);
		// channel.basicAck(deliveryTag, true); // 确认消息成功消费
		channel.basicReject(deliveryTag, true);// 真实环境为true
	}

	// @RabbitListener(queues = RabbitMQConsants.ACK_QUEUE_NAME)
	public void receiveAckQueue(Message message, com.rabbitmq.client.Channel channel) throws IOException {
		byte[] body = message.getBody();
		Long deliveryTag = message.getMessageProperties().getDeliveryTag();

		String string = new String(body);
		logger.info(message.getMessageProperties().getConsumerQueue() + ":" + string);

		// JSONObject jsonObject = JSONObject.fromObject(string);
		// RabbitMqMsgAO rabbitMqMsgAO = (RabbitMqMsgAO) JSONObject.toBean(jsonObject,
		// RabbitMqMsgAO.class);

		/*
		 * if (ProjectModule.EBK.toString().equals(rabbitMqMsgAO.getProject())) { UserBO
		 * userBO = new UserBO(); EbkBO ebkBO = new EbkBO();
		 * 
		 * userBO.setType(2); userBO.setAdCode(new
		 * Integer(rabbitMqMsgAO.getCode()).toString());
		 * 
		 * ebkBO.setBookCode(new Integer(rabbitMqMsgAO.getKey()));
		 * 
		 * ebkService.addUserBook(userBO, ebkBO); }
		 */

		channel.basicAck(deliveryTag, false); // 确认消息成功消费
		// channel.basicReject(deliveryTag, true);
	}
}

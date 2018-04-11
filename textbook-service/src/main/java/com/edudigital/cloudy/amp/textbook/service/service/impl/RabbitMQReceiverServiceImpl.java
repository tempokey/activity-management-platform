package com.edudigital.cloudy.amp.textbook.service.service.impl;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.msg.base.constant.MQQueueConstant;
import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.service.service.IEbkService;
import com.edudigital.cloudy.amp.textbook.service.service.IRabbitMQReceiverService;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

import net.sf.json.JSONObject;

@Service
public class RabbitMQReceiverServiceImpl implements IRabbitMQReceiverService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IEbkService ebkService;

	@RabbitListener(queues = MQQueueConstant.TB_USER_REL_QUEUE_NAME)
	public void receiveUserQueue(Message message, com.rabbitmq.client.Channel channel) throws IOException {

		byte[] body = message.getBody();
		Long deliveryTag = message.getMessageProperties().getDeliveryTag();

		String string = new String(body);
		logger.info(message.getMessageProperties().getConsumerQueue() + ":" + string);

		JSONObject js = JSONObject.fromObject(string);

		OrderDTO orderDTO = (OrderDTO) JSONObject
				.toBean(JSONObject.fromObject((JSONObject.fromObject(js.get("msg"))).get("orderDTO")), OrderDTO.class);
		// 这部分在收到消息回执时，进行添加
		UserDTO userDTO = new UserDTO();
		EbkDTO ebkDTO = new EbkDTO();

		userDTO.setType(2);
		userDTO.setId(orderDTO.getUserId());
		userDTO.setAdCode(orderDTO.getAdCode());

		ebkDTO.setBookId(new Integer(orderDTO.getProductId()));
		ebkDTO.setUpdateTime(new Date());

		int bookId = ebkDTO.getBookId();

		// 添加用户关系
		ebkService.addUserBook(userDTO, ebkDTO);
		if (bookId > 0) {
			ebkService.addBkNum(ebkDTO);
		}

		channel.basicReject(deliveryTag, false);
	}

	// @RabbitListener(queues = MQQueueConstant.TB_ORDERE_QUEUE_NAME)
	public void receiveQueue(Message message, com.rabbitmq.client.Channel channel) throws IOException {
		// logger.info(RabbitMQConsants.QUEUE_NAME + msg.getMsg() + "--Time--" +
		// new
		// Date(msg.getTimestamp()));
		byte[] body = message.getBody();
		Long deliveryTag = message.getMessageProperties().getDeliveryTag();

		String string = new String(body);
		logger.info(message.getMessageProperties().getConsumerQueue() + ":" + string);

		// JSONObject jsonObject = JSONObject.fromObject(string);
		// RabbitMqMsgAO rabbitMqMsgAO = (RabbitMqMsgAO)
		// JSONObject.toBean(jsonObject,
		// RabbitMqMsgAO.class);
		// channel.basicAck(deliveryTag, true); // 确认消息成功消费
		channel.basicReject(deliveryTag, false);// 真实环境为true
	}

	@RabbitListener(queues = MQQueueConstant.TB_ORDER_ACK_QUEUE_NAME)
	public void receiveAckQueue(Message message, com.rabbitmq.client.Channel channel) throws IOException {
		byte[] body = message.getBody();
		Long deliveryTag = message.getMessageProperties().getDeliveryTag();

		String string = new String(body);
		logger.info(message.getMessageProperties().getConsumerQueue() + ":" + string);

		// JSONObject jsonObject = JSONObject.fromObject(string);
		// RabbitMqMsgAO rabbitMqMsgAO = (RabbitMqMsgAO)
		// JSONObject.toBean(jsonObject,
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

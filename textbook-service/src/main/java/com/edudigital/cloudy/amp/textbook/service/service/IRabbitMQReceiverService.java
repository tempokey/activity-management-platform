package com.edudigital.cloudy.amp.textbook.service.service;

import java.io.IOException;

import org.springframework.amqp.core.Message;

/**
 * 
 * @author Tempo
 * @date 2017年8月17日 下午2:38:40
 *
 */
public interface IRabbitMQReceiverService {

	public void receiveUserQueue(Message message, com.rabbitmq.client.Channel channel) throws IOException;
}

package com.edudigital.cloudy.amp.order.service.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MQExchange;
import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MQQueue;

@Configuration
public class RabbitMQProducerConfiguration {

	@Bean
	RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	Queue queue(RabbitAdmin rabbitAdmin) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-message-ttl", 60000);
		Queue queue = new Queue(MQQueue.TB_ORDERE_QUEUE_NAME.toString(), true, false, false, args);
		rabbitAdmin.declareQueue(queue);
		return queue;
	}

	@Bean
	Queue ackQueue(RabbitAdmin rabbitAdmin) {
		Queue queue = new Queue(MQQueue.TB_ORDER_ACK_QUEUE_NAME.toString(), true);
		rabbitAdmin.declareQueue(queue);
		return queue;
	}

	@Bean
	Queue userQueue(RabbitAdmin rabbitAdmin) {
		Queue queue = new Queue(MQQueue.TB_USER_REL_QUEUE_NAME.toString(), true);
		rabbitAdmin.declareQueue(queue);
		return queue;
	}

	// @Bean
	// Queue xxQueue(RabbitAdmin rabbitAdmin) {
	// Map<String, Object> args = new HashMap<String, Object>();
	// args.put("x-message-ttl", 5000);
	// Queue queue = new Queue(MQQueue.TB_TEST_QUEUE_NAME.toString(), true,
	// false, false, args);
	// rabbitAdmin.declareQueue(queue);
	// return queue;
	// }

	@Bean
	TopicExchange exchange(RabbitAdmin rabbitAdmin) {
		TopicExchange topicExchange = new TopicExchange(MQExchange.TOPIC_EXCHANGE_NAME.toString());
		rabbitAdmin.declareExchange(topicExchange);
		return topicExchange;
	}

	@Bean
	Binding bindingExchange(Queue queue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
		Binding binding = BindingBuilder.bind(queue).to(exchange).with(MQQueue.TB_ORDERE_QUEUE_NAME.toString());
		rabbitAdmin.declareBinding(binding);
		return binding;
	}

	@Bean
	Binding bindingExchangeAck(Queue ackQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
		Binding binding = BindingBuilder.bind(ackQueue).to(exchange).with(MQQueue.TB_ORDER_ACK_QUEUE_NAME.toString());
		rabbitAdmin.declareBinding(binding);
		return binding;
	}

	@Bean
	Binding bindingExchangeUser(Queue userQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
		Binding binding = BindingBuilder.bind(userQueue).to(exchange).with(MQQueue.TB_USER_REL_QUEUE_NAME.toString());
		rabbitAdmin.declareBinding(binding);
		return binding;
	}

	// @Bean
	// Binding bindingExchangeXx(Queue xxQueue, TopicExchange exchange,
	// RabbitAdmin rabbitAdmin) {
	// Binding binding =
	// BindingBuilder.bind(xxQueue).to(exchange).with(MQQueue.TB_TEST_QUEUE_NAME.toString());
	// rabbitAdmin.declareBinding(binding);
	// return binding;
	// }

	/**
	 * 生产者用
	 * 
	 * @return
	 */
	@Bean
	public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
		RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
		rabbitMessagingTemplate.setMessageConverter(jackson2Converter());
		rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
		return rabbitMessagingTemplate;
	}

	@Bean
	public MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}

}

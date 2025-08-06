package com.fitness.agent.activityService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMqConfig {
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@Value("${rabbitmq.routing.key}")
	private String routingKey;

    @Bean
    Queue activityQueue() {
		return new Queue(queue, true);
	}
    
    @Bean 
    DirectExchange activityExchange() {
    	return new DirectExchange(exchange);
    }
    
    @Bean
    Binding queueBinding(Queue activityQueue, DirectExchange activityExchange ) {
    	return BindingBuilder
    				.bind(activityQueue)
    				.to(activityExchange)
    				.with(routingKey);
    }
    
    @Bean
    @Qualifier("jsonMessageConvertor")
    MessageConverter jsonMessageConvertor() {
    	return new Jackson2JsonMessageConverter();
    }
    
    @Bean 
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, 
    								@Qualifier("jsonMessageConvertor")MessageConverter jsonMessageConvertor) {
    	RabbitTemplate template = new RabbitTemplate(connectionFactory);
    	template.setMessageConverter(jsonMessageConvertor);
    	template.setExchange(exchange);
    	template.setRoutingKey(routingKey);
    	return template;
    }

}

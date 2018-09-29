package com.example.demo.rabbitMQ.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleRabbitMQSender {
	
	private static final String SIMPLE_QUEUE_NAME = "simple_queue_name";
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Test
	public void sendSimpleRabbitMQMessage(){
		String message = "SimpleRabbitMQMessage do you copy?";
		amqpTemplate.convertAndSend(SIMPLE_QUEUE_NAME,message);
		System.out.println("[SimpleRabbitMQSender] send the message : " + message);
	}
	
	private static final String DISRIBUTE_QUEUE_NAME = "distribute_queue_name";	 
	
	@Test
	public void sendRabbitMQMessage() {
		for (int i = 0; i < 20; i++) {
			String message = "sendRabbitMQMessage ==========" + i;
			amqpTemplate.convertAndSend(DISRIBUTE_QUEUE_NAME,message);
			System.out.println("[DistributeRabbitMQSender] send the message : " + message);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static final String SUBSCRIBE_FANOUT_EXCHANGE= "subscribe_fanout_exchange";	 
	
	@Test
	public void sendRabbitExchangeMQMessage() {
		String message = "sendRabbitExchangeMQMessage ==========";
		amqpTemplate.convertAndSend(SUBSCRIBE_FANOUT_EXCHANGE,"",message);
		System.out.println("[DistributeRabbitMQSender] send the message : " + message);
	}
}

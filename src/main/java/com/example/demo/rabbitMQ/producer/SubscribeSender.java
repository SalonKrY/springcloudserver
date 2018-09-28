package com.example.demo.rabbitMQ.producer;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.example.demo.rabbitMQ.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class SubscribeSender {

	private static final String EXCHANGE_NAME = "subscribe_fanout_exchange";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//1.获得连接
		Connection connection = ConnectionUtil.getConnection();
		//2.声明通道
		Channel channel = connection.createChannel();
		//3.声明一个交换机
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
		
		//发送消息
		String message = "subscribe message";
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		System.out.println("Subscribe send message :" + message + "at " + new Date());
				
		channel.close();
		connection.close();
	}
}

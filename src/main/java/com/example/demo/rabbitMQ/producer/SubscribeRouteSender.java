package com.example.demo.rabbitMQ.producer;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.example.demo.rabbitMQ.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class SubscribeRouteSender {

	private static final String EXCHANGE_NAME = "subscribe_direct_exchange";
	private static final String INFO_ROUTE = "info";
	private static final String ERROR_ROUTE = "error";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//1.获得连接
		Connection connection = ConnectionUtil.getConnection();
		//2.声明通道
		Channel channel = connection.createChannel();
		//3.声明一个交换机
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		
		//发送消息
		String infoMessage = "subscribe info message";
		channel.basicPublish(EXCHANGE_NAME, INFO_ROUTE, null, infoMessage.getBytes());
		System.out.println("Subscribe send message :" + infoMessage + "at " + new Date());
		
		//发送消息
		String errorMesssage = "subscribe error message";
		channel.basicPublish(EXCHANGE_NAME, ERROR_ROUTE, null, errorMesssage.getBytes());
		System.out.println("Subscribe send message :" + errorMesssage + "at " + new Date());
		
		channel.close();
		connection.close();
	}
}

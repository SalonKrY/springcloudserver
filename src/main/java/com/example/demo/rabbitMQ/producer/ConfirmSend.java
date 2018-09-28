package com.example.demo.rabbitMQ.producer;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.example.demo.rabbitMQ.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ConfirmSend {
private static final String QUEUE_NAME = "confirm_queue_name";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		//1.获得连接
		Connection connection = ConnectionUtil.getConnection();
		
		//2.创建通道
		Channel channel = connection.createChannel();
		
		//3.申明队列，没有就创建一个
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//开启confirm
		channel.confirmSelect();
		
		//4.发送消息
		String message = "Simple RabbitMQ send: Hello World.";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println("SimpleSender public message：" + message + "at " + new Date());
		
		if (channel.waitForConfirms()) {
			System.out.println(" Confirm message : '" + message + "' ok ");
		} else {
			System.out.println(" Confirm message : '" + message + "' ok ");
		}
		
		//5.关闭连接
		channel.close();
		connection.close();
	}
}

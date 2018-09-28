package com.example.demo.rabbitMQ.producer;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.example.demo.rabbitMQ.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class TxSender {
	
	private static final String QUEUE_NAME = "tx_queue_name";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//1.获得连接
		Connection connection = ConnectionUtil.getConnection();
		
		//2.创建通道
		Channel channel = connection.createChannel();
		
		//3.申明队列，没有就创建一个
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		try {
			//开启事务
			channel.txSelect();
			
			
			String message = "TxSender RabbitMQ send: Hello World.";
			//4.发送消息
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println("[TxSender] public message：" + message + "at " + new Date());
			int i = 1/0;
			
			//提交事务
			channel.txCommit();
		} catch (Exception e) {
			System.out.println("[TxSender] Publish Exception");
			channel.txRollback();
		}
		
		//5.关闭连接
		channel.close();
		connection.close();
	}
}

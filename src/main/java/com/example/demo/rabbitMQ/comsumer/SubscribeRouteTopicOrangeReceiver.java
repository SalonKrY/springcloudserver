package com.example.demo.rabbitMQ.comsumer;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.example.demo.rabbitMQ.util.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class SubscribeRouteTopicOrangeReceiver {

	private static final String EXCHANGE_NAME = "subscribe_topic_exchange";
	private static final String QUEUE_NAME = "topic_orange_queue";
	private static final String ROUTE = "topic.orange.*";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//1.获得连接
		Connection connection = ConnectionUtil.getConnection();
		//2.声明通道
		Channel channel = connection.createChannel();
		//3.声明要消费的队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//4.绑定交换机
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE);
		//5.同时间最多消费一个message
		channel.basicQos(1);
		
		//创建消费回调
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag,Envelope envelop,AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body);
				System.out.println("[TopicOrangeReceiver] receive message : " + message + " at " + new Date());
				
				try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("[TopicOrangeReceiver] receive done ");
                    channel.basicAck(envelop.getDeliveryTag(), false);
                }
			}
		};
		
		channel.basicConsume(QUEUE_NAME, false, consumer);
	}
}

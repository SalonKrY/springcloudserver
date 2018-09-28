package com.example.demo.rabbitMQ.producer;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.example.demo.rabbitMQ.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class SimpleSender {
	
	private static final String QUENE_NAME = "simple_quene_name";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//1.获得连接
		Connection connection = ConnectionUtil.getConnection();
		
		//2.创建通道
		Channel channel = connection.createChannel();
		
		//3.申明队列，没有就创建一个
		/**
		 * Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, 
		 *    boolean autoDelete, Map<String, Object> arguments) throws IOException;
		 * queue: 队列名称
		 * durable: 是否持久化，true表示RabbitMQ重启后，队列仍然存在
		 * exclusive: true表示当前连接的专用队列，在连接断开后，会自动删除该队列
		 * autoDelete: true 表示当没有任何消费者使用时，自动删除该队列
		 * arguments: 该队列其他配置参数
		 */
		channel.queueDeclare(QUENE_NAME, false, false, false, null);
		
		String message = "Simple RabbitMQ send: Hello World.";
		//4.发送消息
		/**
		 * void basicPublish(String exchange, String routingKey, BasicProperties props, 
		 * byte[] body) throws IOException;
		 * exchange: 指定交换机，这里为简单队列，不需要使用，默认""即可，不能为null
		 * routingKey: 路由key，这里为队列名称
		 * props: 其他消息属性，路由头信息等等
		 * body: 消息byte内容
		 */
		channel.basicPublish("", QUENE_NAME, null, message.getBytes());
		System.out.println("SimpleSender public message：" + message + "at " + new Date());
		
		//5.关闭连接
		channel.close();
		connection.close();
	}
}

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

public class SimpleReceiver {

	private static final String QUEUE_NAME = "simple_queue_name";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
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
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		
		//4.创建一个回调的消费者
		Consumer comsumer = new DefaultConsumer(channel){
			@Override
            public void handleDelivery(String consumerTag, Envelope envelope,
            		AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 接收到的消息
                String message = new String(body);
                System.out.println("SimpleReceiver recevie message：" + message + "at " + new Date());
            }
		};
		channel.basicConsume(QUEUE_NAME, comsumer);
	}
}

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

public class TxReceiver {

	private static final String QUENE_NAME = "tx_quene_name";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		//1.获得连接
		Connection connection = ConnectionUtil.getConnection();
		
		//2.创建通道
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUENE_NAME, false, false, false, null);
		
		
		//4.创建一个回调的消费者
		Consumer comsumer = new DefaultConsumer(channel){
			@Override
            public void handleDelivery(String consumerTag, Envelope envelope,
            		AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 接收到的消息
                String message = new String(body);
                System.out.println("[TxReceiver] recevie message：" + message + "at " + new Date());
            }
		};
		channel.basicConsume(QUENE_NAME, comsumer);
	}
}

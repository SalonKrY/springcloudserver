package com.example.demo.rabbitMQ.producer;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

import com.example.demo.rabbitMQ.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

public class AsyncConfirmSend {
private static final String QUEUE_NAME = "async_confirm_queue_name";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		//1.获得连接
		Connection connection = ConnectionUtil.getConnection();
		
		//2.创建通道
		Channel channel = connection.createChannel();
		
		//3.申明队列，没有就创建一个
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//开启confirm
		channel.confirmSelect();
		
		SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
		
		channel.addConfirmListener(new ConfirmListener() {
            // 有效的应答
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
            	System.out.println("Ack, SeqNo: " + deliveryTag + ", multiple: " + multiple);
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }
            
            // 有问题的应答
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("Nack, SeqNo: " + deliveryTag + ", multiple: " + multiple);
                // TODO 处理未确认的应答
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }
        });
	
		//4.发送消息
		for (int i = 0; i < 10; i++) {
            long nextSeqNo = channel.getNextPublishSeqNo();
            String message = "hello, confirm message ";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            confirmSet.add(nextSeqNo);
        }
		 
		//5.关闭连接
		channel.close();
		connection.close();
		
	}
}

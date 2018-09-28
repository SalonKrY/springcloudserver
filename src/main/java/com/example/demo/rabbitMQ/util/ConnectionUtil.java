package com.example.demo.rabbitMQ.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

	private static final String HOST = "127.0.0.1";
	private static final int PORT = 5672;
	private static final String USER = "admin";
	private static final String PASSWORD = "admin";
	
	public static Connection getConnection() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername(USER);
		factory.setPassword(PASSWORD);
		factory.setVirtualHost("/");
		return factory.newConnection();
	}
}

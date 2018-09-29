package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpingbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpingbootApplication.class, args);
	}
}

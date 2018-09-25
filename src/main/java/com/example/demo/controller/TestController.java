package com.example.demo.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.User;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/hw")
//	跨域配置
//	@CrossOrigin(origins="http://localhost:8088")
	public String hw() {
		return "hello world";
	}
	
	@GetMapping("/wait")
	public String w() {
		return "wait la";
	}
	
	@GetMapping("/testUser")
	public User testUser() {
		User user = new User();
		user.setId("1");
		user.setUserName("test");
		user.setPassword("test");
		user.setBirthday(new Date());
		return user;
	}
	
	@GetMapping("/testException")
	public User testException() throws Exception{
		 throw new Exception("发生异常");
	}
}

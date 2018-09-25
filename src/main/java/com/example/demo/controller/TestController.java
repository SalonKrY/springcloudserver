package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/hw")
	public String hw() {
		return "hello world";
	}
	
	@GetMapping("/wait")
	public String w() {
		return "wait la";
	}
}

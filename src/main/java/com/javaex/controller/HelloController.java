package com.javaex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
	@GetMapping ("/test") 
	public String hello () {
		
		System.out.println("HelloController.test()");
		System.out.println("안녕하게요");
		
		return "api서버테스트";
		
	}
	
	
	

}

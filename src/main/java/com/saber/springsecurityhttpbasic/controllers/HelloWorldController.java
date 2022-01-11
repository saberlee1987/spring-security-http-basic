package com.saber.springsecurityhttpbasic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloWorldController {
	
	@GetMapping
	public String helloWorld(){
		return "Hello World";
	}
}

package com.daimabaike.example.gateway;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class AController {
	
	@RequestMapping("/hw")
	public Mono<String> hw() {
		
		return Mono.just("hi" + new Date());
	}
	@RequestMapping("/hw2")
	public String hw2() {
		
//		return Mono.just("hi" + new Date());
//		return "hw2" + new Date();
		throw new NullPointerException("ddd");
	}
	
}

package com.daimabaike.example.gateway;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class AController {

	@RequestMapping("/404")
	public Map<String, Object> bizException() {
		Map<String, Object> result = new HashMap<>();

		result.put("error_code", "40000");
		result.put("error_message", "Not Exists");

		return result;
	}
	
	@RequestMapping("/hw")
	public Mono<String> hw() {
		
		return Mono.just("hi" + new Date());
//		return "hi" + new Date();
	}
	@RequestMapping("/hw2")
	public String hw2() {
		
//		return Mono.just("hi" + new Date());
//		return "hw2" + new Date();
		throw new NullPointerException("ddd");
	}
}

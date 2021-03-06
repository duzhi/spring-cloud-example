package com.daimabaike.example.gateway;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AController {
	
	@RequestMapping("/shutdown/{a}")
	public Mono<String> hw(@PathVariable String a) {
		
		MyHealthIndicator.shutdown = Boolean.parseBoolean(a);
		
		return Mono.just("shutdown=" + MyHealthIndicator.shutdown);
	}
	
	@PostMapping("/hw")
	public Mono<String> hw() {
		return Mono.just("post hi" + new Date());
	}
	
	@GetMapping("/hw2")
	public Mono<String> hw2() {
		return Mono.just("get hi" + new Date());
	}
	@GetMapping("/user")
	public Mono<User> user() {
		
	User u = new User();
	u.setAge(21);		
	u.setName("zsddf");
		return Mono.just(u);
	}
	
	@GetMapping("/fluxjust")
	public Flux<String> fluxjust() {
		return Mono.just("Hello World 1:" + new Date()).concatWith(Mono.just("Hello World 2:" + new Date()));
	}
	
	@PostMapping("/hw2")
	public Mono<String> fgg() {
		return Mono.just("post hi" + new Date());
	}
	
	@GetMapping("/hw3")
	public String hw3() {
		
		throw new NullPointerException("ddd");
	}
}

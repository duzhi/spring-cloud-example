package com.daimabaike.example.bar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务注册，只需要配置上 spring.cloud.consul.discovery.service-name 即可，相应的 http 服务就发布出去了
 * 
 * @author qibiao
 */
@SpringBootApplication
//@EnableBinding({ Source.class, Sink.class })
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

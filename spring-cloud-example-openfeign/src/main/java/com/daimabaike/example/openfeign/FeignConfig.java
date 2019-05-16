package com.daimabaike.example.openfeign;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;

//@Configuration
public class FeignConfig {
//	@Bean
    public Retryer feignRetryer() {
         return new Retryer.Default(100, SECONDS.toMillis(20), 5);
    }
}

package com.daimabaike.example.gateway;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("my1")
public class MyHealthIndicator implements HealthIndicator{

	public static boolean shutdown = false;
	
	@Override
	public Health health() {
		
//		Assert.notNull("","");
		
		if(shutdown) {
			return Health.down().withDetail("code",404).build();
		} 
		return Health.up().withDetail("code",200).build();
	}
	
}

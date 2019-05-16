package com.daimabaike.example.bar;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

	@Value("${server.port}")
	String port;

	boolean delay = false;
	
	@GetMapping("/hi")
	public Map<String,Object> home(@RequestParam String name) {//

		try {
			if(delay) {
				Thread.sleep(5100);
			}
			
			if(new Random().nextInt(10) > 5) {
				throw new NullPointerException("NPE >5");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("HiController " + port);
		
		Map<String,Object> map = new HashMap<>();
		map.put("info", "hi " + name + ",i am from port:" + port
				+ "<br /> <br />  <a href=\"https://lingquan.5aiyoo.com/comp/test.html\">file</a>   <br /> <br /> <a href=\"https://lingquan.5aiyoo.com/comp/qytx_jrml_2104.apk\">sdsd</a>");

	return map;
	}

	@GetMapping("/test/{a}")
	public String xxx(@PathVariable String a) {//
		
		delay = Boolean.parseBoolean(a);
		
		return "delay is " + delay;
	}
	
	@GetMapping("/get")
	public String xx() {//
		
		return "delay is " + delay;
	}
}

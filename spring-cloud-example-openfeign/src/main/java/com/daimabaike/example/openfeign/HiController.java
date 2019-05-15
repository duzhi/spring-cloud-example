package com.daimabaike.example.openfeign;

import java.util.Date;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController implements ApplicationContextAware{

	@Autowired
	HiClient hiClient;
	
//	        setProxy(new java.net.);
		
	
	@GetMapping("/hi")
	public String sayHi(@RequestParam(defaultValue = "hiw", required = false) String name) {
		return hiClient.sayHi(name);
	}
	@GetMapping("get")
	public String sss1() {
		return "foo get";
	}
	@GetMapping("/test/{a}")
	public String sss(@PathVariable String a) {
		return "foo test";
	}
	@GetMapping("/sf")
	public String saySf(@RequestParam(defaultValue = "sf", required = false) String name) {
		
//	@Configuration: com.daimabaike.example.openfeign.HiClient$HiClientImpl$$EnhancerBySpringCGLIB$$925ab998@41676a9e
//	@Component:	com.daimabaike.example.openfeign.HiClient$HiClientImpl@53bbfefb

//		rest.set
		
		System.out.println(	ac.getBean(HiClient.HiClientImpl2.class));
		
		return name + new Date();
	}
	ApplicationContext ac;
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		this.ac = ac;
	}
}
//
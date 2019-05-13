package com.daimabaike.example.openfeign;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

	@Autowired
	HiClient hiClient;

	@GetMapping("/hi")
	public String sayHi(@RequestParam(defaultValue = "hiw", required = false) String name) {
		return hiClient.sayHi(name);
	}

	@GetMapping("/sf")
	public String saySf(@RequestParam(defaultValue = "sf", required = false) String name) {
		return name + new Date();
	}
}
//
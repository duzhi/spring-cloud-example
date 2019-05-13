package com.daimabaike.example.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("biz-bar")
public interface HiClient {
	@RequestMapping(method = RequestMethod.GET, value = "/hi")
	String sayHi(@RequestParam("name") String name);

}

package com.daimabaike.example.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url="https://www.baidu.com" ,name="baidu")
public interface BaiduClient {

	@RequestMapping(method = RequestMethod.GET, value = "/")
	String home();
	
}

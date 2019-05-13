package com.daimabaike.example.bar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

	@Value("${server.port}")
	String port;

	@GetMapping("/hi")
	public String home(@RequestParam String name) {//

		System.out.println("HiController " + port);
		return "hi " + name + ",i am from port:" + port
				+ "<br /> <br />  <a href=\"https://lingquan.5aiyoo.com/comp/test.html\">file</a>   <br /> <br /> <a href=\"https://lingquan.5aiyoo.com/comp/qytx_jrml_2104.apk\">sdsd</a>";
	}

	@GetMapping("/test/{a}")
	public String xxx(@PathVariable String a) {//
		return "path is /test/" + a;
	}
}

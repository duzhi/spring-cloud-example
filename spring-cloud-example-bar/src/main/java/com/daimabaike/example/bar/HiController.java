package com.daimabaike.example.bar;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daimabaike.example.common.ClientException;
import com.daimabaike.example.common.ServerException;
import com.daimabaike.example.common.User;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HiController {

	@Value("${server.port}")
	String port;

	boolean delay = false;

	@Autowired
	Tracer tracer;

	@GetMapping("/hi")
	public Result<User> home(@RequestParam String name) {//

		if (new Random().nextInt(10) > 5) {

			throw new ClientException(40001, "客户端错误，检查");

		}
		log.info("tracerId{}", tracer.currentSpan().context().traceIdString());

		log.info("HiController " + port);

		User user = new User();

		user.setName(name);
		user.setAge(12);

		user.setSignature("hi " + name + ",i am from port:" + port
				+ "<br /> <br />  <a href=\"https://lingquan.5aiyoo.com/comp/test.html\">file</a>   <br /> <br /> <a href=\"https://lingquan.5aiyoo.com/comp/qytx_jrml_2104.apk\">sdsd</a>");

		return Result.ok(user);
	}

	@GetMapping("/test/{a}")
	public String xxx(@PathVariable String a) {//

		delay = Boolean.parseBoolean(a);

		return "delay is " + delay;
	}

	@GetMapping("/sleep/{times}")
	public String xx(@PathVariable int times) {//
		
		if(times > 10) {
			times = 10;
		}
		
		try {
			TimeUnit.SECONDS.sleep(times);
		} catch (InterruptedException e) {
			// 
			//throw e;
			//Thread.currentThread().interrupt();
			throw new ServerException(50002,"113");
		}
		
		log.info("tracerId{}", tracer.currentSpan().context().traceIdString());

		return "delay is " + delay;
	}
}

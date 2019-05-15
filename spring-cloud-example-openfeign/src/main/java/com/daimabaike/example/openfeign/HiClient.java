package com.daimabaike.example.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.daimabaike.example.openfeign.HiClient.HiClientImpl2;

import feign.hystrix.FallbackFactory;

@FeignClient(name = "biz-bar", configuration = FeignConfig.class,fallbackFactory = HiClientImpl2.class)
public interface HiClient {
	@RequestMapping(method = RequestMethod.GET, value = "/hi")
	String sayHi(@RequestParam("name") String name);

//	@Component
//	public final class HiClientImpl implements HiClient {
//		@Override
//		public String sayHi(String name) {
//			return "rongduan";
//		}
//	}

	@Component
	public final class HiClientImpl2 implements FallbackFactory<HiClient> {
		@Override
		public HiClient create(Throwable t) {
			return new HiClient() {
				@Override
				public String sayHi(String name) {
					t.printStackTrace();
					return name + t.getMessage();
				}
			};
		}
	}

}

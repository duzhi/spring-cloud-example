package com.daimabaike.example.openfeign;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.daimabaike.example.openfeign.HiClient.HiClientImpl;

//fallbackFactory = HiClientImpl2.class
//, fallback=HiClientImpl.class
@FeignClient(name = "biz-bar")
public interface HiClient {
	@RequestMapping(method = RequestMethod.GET, value = "/hi")
	Map<String,Object> sayHi(@RequestParam("name") String name);

	@Component
	public final class HiClientImpl implements HiClient {
		@Override
		public Map<String,Object> sayHi(String name) {
			Map<String,Object> map = new HashMap<>();
			map.put("info", "fallback");
			return map;
		}
	}

//	@Component
//	public final class HiClientImpl2 implements FallbackFactory<HiClient> {
//		@Override
//		public HiClient create(Throwable t) {
//			return new HiClient() {
//				@Override
//				public String sayHi(String name) {
//					t.printStackTrace();
//					return name + t.getMessage();
//				}
//			};
//		}
//	}

}

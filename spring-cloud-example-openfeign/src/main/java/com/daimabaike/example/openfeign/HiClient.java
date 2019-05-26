package com.daimabaike.example.openfeign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.daimabaike.example.common.ClientException;
import com.daimabaike.example.common.ServerException;
import com.daimabaike.example.common.User;

import feign.hystrix.FallbackFactory;

@FeignClient(name = "biz-bar" ,configuration= {FeignConfig.class})
public interface HiClient {

	@RequestMapping(method = RequestMethod.GET, value = "/hi")
	ResultUser sayHi(@RequestParam("name") String name);
	
	public class ResultUser extends Result<User>{
		public ResultUser() {
		//	super.setData(new User());
		}
	}
	
	// fallback 发生的条件是 超时，5xx 错误？4xx 错误不会走这个？
	@Component
	public final class HiClientImpl2 implements FallbackFactory<HiClient> {
		final Logger log = LoggerFactory.getLogger(HiClientImpl2.class);

		@Override
		public HiClient create(Throwable t) {
			return new HiClient() {
				@Override
				public ResultUser sayHi(String name) {

					log.error("error sayHi:", t.getClass());
					log.error("error sayHi:", t);

					if (t instanceof ClientException) {

						throw (ClientException) t;

					}

					throw new ServerException(50006, "dsd", t);
				}
			};
		}
	}

}

package com.daimabaike.example.openfeign;

import java.io.IOException;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONObject;
import com.daimabaike.example.common.ClientException;
import com.daimabaike.example.common.ServerException;

import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
	static final Logger log = LoggerFactory.getLogger(FeignConfig.class);

	// @Autowired(required = false)
	// private Logger log;

	// @Bean
	// public Retryer feignRetryer() {
	// return new Retryer.Default(100, SECONDS.toMillis(20), 5);
	// }

	// Decoder}, {@link feign.codec.Encoder}, {@link feign.Contract}

	@Bean
	public Decoder createDecoder() {
		log.info("FeignConfig.createDecoder()=============================================================");
		return new Decoder() {

			@Override
			public Object decode(Response response, Type type) {
				log.info("=============================================================" + type);
				
				try {
					String rsp = Util.toString(response.body().asReader());
					log.info("rsp ==={}, ", rsp);
					// HttpStatus httpStatus = HttpStatus.valueOf(response.status());

					// if (httpStatus.is2xxSuccessful()) {
					return JSONObject.parseObject(rsp, type);
					// }
//return rsp;
					// throw new ServerException("50003", "2324342");
				} catch (Throwable e) {
					throw new ServerException(50004, "是的方式发送到发");
				}

			}
		};
	}

	@Bean
	public ErrorDecoder dd() {
		return new ErrorDecoder() {

			@Override
			public Exception decode(String methodKey, Response response) {
				HttpStatus httpStatus = HttpStatus.valueOf(response.status());
				try {
					String rsp = Util.toString(response.body().asReader());
					log.info("rsp error ==={}, ", rsp);
					JSONObject jj = JSONObject.parseObject(rsp);

					String msg = jj.getString("msg");
					int code = jj.getIntValue("code");
					log.info("rsp code error ==={}, ", code);
					log.info("rsp msg error ==={}, ", msg);
					if (httpStatus.is4xxClientError()) {

						ClientException ce = new ClientException(msg);
						ce.setCode(code);
						throw ce;
					}
					if (httpStatus.is5xxServerError()) {
						ServerException ce = new ServerException(msg);
						ce.setCode(code);
						throw ce;
					}
					throw new ServerException(50003, "2324342");
				}

				catch (IOException e) {
					throw new ServerException(50004, "是的方式发送到发");
				}

			}

		};
	}

}

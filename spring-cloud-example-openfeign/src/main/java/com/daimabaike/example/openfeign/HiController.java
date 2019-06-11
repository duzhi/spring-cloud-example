package com.daimabaike.example.openfeign;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.daimabaike.example.common.User;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HiController implements ApplicationContextAware {

	@Autowired
	HiClient hiClient;
	@Autowired
	BaiduClient baiduClient;
	// setProxy(new java.net.);
	// @Autowired
	// FeignClientsConfiguration fc;

	Tracer tracer;

	// @Autowired Decoder decoder;

	@Autowired
    @Qualifier("phoenixJdbcTemplate")
	private JdbcTemplate xJdbcTemplate;
	
	private NamedParameterJdbcTemplate njt;
	
	@GetMapping("/hi")
	public Result<User> sayHi(@RequestParam(defaultValue = "hiw", required = false) String name) {

//		Arrays.
		xJdbcTemplate.queryForList("where k=:k",new Object[] {""}, User.class);
		
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("name", "lusc");
		njt.queryForList("", sps , User.class);
		
		njt.batchUpdate("", new SqlParameterSource[] {sps});
		
		log.info("tracerId{}", tracer.currentSpan().context().traceIdString());
		// log.info("xxx{}",decoder.getClass());

		return hiClient.sayHi(name);
	}

	@GetMapping("get")
	public String sss1() {
		return "foo get";
	}

	@GetMapping("bd")
	public String bd() {
		return baiduClient.home();
	}

	@GetMapping("/test/{a}")
	public String sss(@PathVariable String a) {
		return "foo test";
	}

	@GetMapping("/sf")
	public String saySf(@RequestParam(defaultValue = "sf", required = false) String name) {

		// @Configuration:
		// com.daimabaike.example.openfeign.HiClient$HiClientImpl$$EnhancerBySpringCGLIB$$925ab998@41676a9e
		// @Component: com.daimabaike.example.openfeign.HiClient$HiClientImpl@53bbfefb

		// rest.set

		MappingJackson2HttpMessageConverter c = new MappingJackson2HttpMessageConverter() {
			@Override
			public Object read(Type type, @Nullable Class<?> contextClass, HttpInputMessage inputMessage)
					throws IOException, HttpMessageNotReadableException {
				
				// 
				Object obj = super.read(type, contextClass, inputMessage);
				
				return "";
			}
		};

		RestTemplate t = new RestTemplate(Arrays.asList(c));
		t.responseEntityExtractor(List.class);
		t.setErrorHandler(null);
		
		t.exchange("", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<String>>() {}, "").getBody();
		
		
		
		return name + new Date();
	}

	ApplicationContext ac;

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		this.ac = ac;
	}
}
//
package com.daimabaike.example.gateway;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class AppTest {
	@Test
	public void testApp() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void test1(){
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8030));
		SimpleClientHttpRequestFactory cf = new SimpleClientHttpRequestFactory();
		cf.setProxy(proxy);
		RestTemplate rest = new RestTemplate(cf);
		
		System.out.println(rest.getForEntity("http://a.daimabaike.com:8110/hi", String.class));
		
	}
}

package com.daimabaike.example.gateway;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(-10)
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public Map<String, Object> handleGlobalException(Exception e) {
		System.out.println("全局异常 ex={}");
		e.printStackTrace();
		Map<String, Object> map = new HashMap<>();
		map.put("ret", 40000);
		map.put("msg", e.getClass() + e.getMessage() + e.getCause());
		return map;
	}
}

package com.daimabaike.example.openfeign;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalExceptionHandler {
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleGlobalException(Exception e) {
		System.out.println("全局异常 ex={}");
		e.printStackTrace();
		Map<String, Object> map = new HashMap<>();
		map.put("error_code", 40000);
		map.put("error_msg", e.getClass() + e.getMessage() + e.getCause());
		return map;
	}
}

package com.daimabaike.example.bar;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Throwable.class)
	public Object ss(Throwable t) {
		
		Map<String,Object> map=new HashMap<>();
		map.put("code", 40001);
		map.put("msg", t.getMessage());
		
		return map;
	}
	
}

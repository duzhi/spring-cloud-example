package com.daimabaike.example.bar;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daimabaike.example.common.ClientException;

@RestControllerAdvice
public class ExceptionController {

	final Logger log = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Map<String, Object>> ex(ClientException t) {
		Map<String, Object> map = new HashMap<>();
		
		log.info("server callback client error:{}{}", t.getCode(), t.getMessage());
		
		map.put("code", t.getCode());
		map.put("message", t.getMessage());
		log.info("client error:{}{}", t.getCode(), t.getMessage());

		return ResponseEntity.status(HttpStatus.OK).header("x-app-flag", "1").body(map);
	}

}

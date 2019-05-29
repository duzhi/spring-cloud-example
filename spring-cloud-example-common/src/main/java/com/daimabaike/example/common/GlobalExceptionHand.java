package com.daimabaike.example.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class GlobalExceptionHand {

	final Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(ClientException.class)
	public ResponseEntity<Map<String, Object>> ex(ClientException t) {
		Map<String, Object> map = new HashMap<>();

		log.info("server callback client error:{}{}", t.getCode(), t.getMessage());

		map.put("code", t.getCode());
		map.put("message", t.getMessage());
		log.info("client error:{}{}", t.getCode(), t.getMessage());

		return ResponseEntity.status(HttpStatus.OK).header("x-app-flag", "1").body(map);
	}
}

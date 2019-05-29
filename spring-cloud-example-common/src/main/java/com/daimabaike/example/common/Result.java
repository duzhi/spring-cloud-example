package com.daimabaike.example.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {

	private int code;
	private String message;
	private T data;
	
	public static <T> Result<T> ok(T t) {
		Result<T> r = new Result<>();
		r.code = 200;
		r.data = t;
		r.message = "success";
		return r;
	}
}

package com.daimabaike.example.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;

	public ClientException() {

	}

	public ClientException(String message) {
		super(message);
	}

	public ClientException(int code, String message) {
		super(message);
		this.code = code;
	}

}

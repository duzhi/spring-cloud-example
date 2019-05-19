package com.daimabaike.example.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;

	public ServerException() {

	}

	public ServerException(String message) {
		super(message);
	}

	public ServerException(int code, String message) {
		super(message);
		this.code = code;
	}
	
	public ServerException(int code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }
	
}

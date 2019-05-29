package com.daimabaike.example.common;

public class AssertUtils {

	public static <T> void success(Result<T> r) {

		if (r.getCode() == 0) {
			return;
		}

		if (r.getCode() < 50000) {
			throw new ClientException(r.getCode(), r.getMessage());
		}

		throw new ServerException();

	}

}

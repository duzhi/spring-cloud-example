package com.daimabaike.example.gateway;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Component
public class ResponseResultHandler extends ResponseBodyResultHandler {

	protected Logger logger = LoggerFactory.getLogger(ResponseResultHandler.class);

	public ResponseResultHandler(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver) {

		super(writers, resolver);
	}

	@Override
	public boolean supports(HandlerResult result) {
		boolean isMonoOrFlux = result.getReturnType().resolve() == Mono.class
				|| result.getReturnType().resolve() == Flux.class;
		boolean isAlreadyResponse = result.getReturnType().resolveGeneric(0) == Result.class;
		return isMonoOrFlux && !isAlreadyResponse;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	private Mono<Result> methodForParams() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
		
		Object body;
		if (result.getReturnType().resolve() == Flux.class) {
			Flux<Object> a = (Flux<Object>) result.getReturnValue();
			
			body = a.reduce((m,n)->(String)m+(String)n).map(Result::ok).defaultIfEmpty(Result.ok());;
			
		} else {
			Mono<Object> a = (Mono<Object>) result.getReturnValue();
			// modify the result as you want
			body = a.map(Result::ok).defaultIfEmpty(Result.ok());

		}

		MethodParameter param = null;
		try {
			param = new MethodParameter(ResponseResultHandler.class.getDeclaredMethod("methodForParams"), -1);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return writeBody(body, param, exchange);
	}

}

package com.daimabaike.example.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.netty.buffer.UnpooledByteBufAllocator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("Welcome to AuthGlobalFilter.");
		ServerHttpRequest request = exchange.getRequest();
		String sign = request.getQueryParams().getFirst("sign");

		System.out.println(request.getQueryParams().get("sign"));

		String token = "1234";
		if (token.equals(sign)) {
			return chain.filter(exchange);
		}
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(HttpStatus.UNAUTHORIZED);

		DataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
		DataBuffer dataBuffer = nettyDataBufferFactory.wrap("<div>403 Forbidden</div>".getBytes());

		Mono<DataBuffer> flux = Mono.just(dataBuffer);

		return response.writeWith(flux);
	}

	@Override
	public int getOrder() {
		return 0;
	}
}

package com.daimabaike.example.gateway;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.DefaultClientResponse;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ResponseGlobalFilter implements GlobalFilter, Ordered {

	private static final String SUCCESS_PREFIX = "{\"msg\":\"suczcess\",\"ret\":200,\"data\":";
	private static final String SUCCESS_SUFFIX = "}";

	// private final Config config;
	//
	// public ModifyResponseGatewayFilter(Config config) {
	// this.config = config;
	// }

	public class ResponseAdapter implements ClientHttpResponse {

		private final Flux<DataBuffer> flux;

		private final HttpHeaders headers;

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public ResponseAdapter(Publisher<? extends DataBuffer> body, HttpHeaders headers) {
			this.headers = headers;
			if (body instanceof Flux) {
				flux = (Flux) body;
			} else {
				flux = ((Mono) body).flux();
			}
		}

		@Override
		public Flux<DataBuffer> getBody() {
			return flux;
		}

		@Override
		public HttpHeaders getHeaders() {
			return headers;
		}

		@Override
		public HttpStatus getStatusCode() {
			return null;
		}

		@Override
		public int getRawStatusCode() {
			return 0;
		}

		@Override
		public MultiValueMap<String, ResponseCookie> getCookies() {
			return null;
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		ServerHttpResponseDecorator responseDecorator = new ServerHttpResponseDecorator(exchange.getResponse()) {

			@SuppressWarnings("rawtypes")
			@Override
			public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

				Class inClass = String.class;// config.getInClass();
				Class outClass = String.class;// config.getOutClass();
				RewriteFunction<String, String> d = (exchange, s) -> {

					if (!exchange.getResponse().getHeaders().containsKey("x-app-flag")) {
						return Mono.just(SUCCESS_PREFIX + s + SUCCESS_SUFFIX);
					}

					return Mono.just(s);

				};
				String originalResponseContentType = exchange.getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
				HttpHeaders httpHeaders = new HttpHeaders();
				// httpHeaders.get
				// explicitly add it in this way instead of
				// 'httpHeaders.setContentType(originalResponseContentType)'
				// this will prevent exception in case of using non-standard media
				// types like "Content-Type: image"
				httpHeaders.add(HttpHeaders.CONTENT_TYPE, originalResponseContentType);
				ResponseAdapter responseAdapter = new ResponseAdapter(body, httpHeaders);

				if (!getStatusCode().is2xxSuccessful()) {
					return super.writeWith(body);
				}

				// responseAdapter.c
				DefaultClientResponse clientResponse = new DefaultClientResponse(responseAdapter,
						ExchangeStrategies.withDefaults());

				Mono modifiedBody = clientResponse.bodyToMono(inClass)
						.flatMap(originalBody -> d.apply(exchange, (String) originalBody));

				BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, outClass);
				CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange,
						exchange.getResponse().getHeaders());
				return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
					Flux<DataBuffer> messageBody = outputMessage.getBody();
					HttpHeaders headers = getDelegate().getHeaders();

				//	this.setStatusCode(HttpStatus.OK);

					if (!headers.containsKey(HttpHeaders.TRANSFER_ENCODING)) {
						messageBody = messageBody.doOnNext(data -> headers.setContentLength(data.readableByteCount()));
					}

					return getDelegate().writeWith(messageBody);
				}));
			}

			@Override
			public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
				return writeWith(Flux.from(body).flatMapSequential(p -> p));
			}
		};

		return chain.filter(exchange.mutate().response(responseDecorator).build());
	}

	@Override
	public int getOrder() {
		return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
	}

}

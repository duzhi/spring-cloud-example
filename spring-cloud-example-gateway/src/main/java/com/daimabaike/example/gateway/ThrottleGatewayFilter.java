package com.daimabaike.example.gateway;

/**
 * 节流网关过滤器
 */
public class ThrottleGatewayFilter {//implements GatewayFilter {

//	private static final Log log = LogFactory.getLog(ThrottleGatewayFilter.class);
//
//	int capacity;
//
//	int refillTokens;
//
//	int refillPeriod;
//
//	TimeUnit refillUnit;
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//		TokenBucket tokenBucket = TokenBuckets.builder().withCapacity(capacity)
//				.withFixedIntervalRefillStrategy(refillTokens, refillPeriod, refillUnit).build();
//
//		// TODO: get a token bucket for a key
//		log.debug("TokenBucket capacity: " + tokenBucket.getCapacity());
//		boolean consumed = tokenBucket.tryConsume();
//		if (consumed) {
//			return chain.filter(exchange);
//		}
//		exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
//		return exchange.getResponse().setComplete();
//	}

}

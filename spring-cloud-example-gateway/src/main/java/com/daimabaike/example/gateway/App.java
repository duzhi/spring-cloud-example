package com.daimabaike.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@EnableAutoConfiguration
public class App {

	// String uri;

	public static void main(String[] args) {
//		org.springframework.jdbc.CannotGetJdbcConnectionException
		SpringApplication.run(App.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		// @formatter:off
		return builder.routes()
				// 路径路由
				// .route("path_route", r -> r.path("/*")
				// .uri("http://localhost:8000"))
				// .route("path_route2", r -> r.path("/foo/*")
				// .uri("http://localhost:8300"))

//				.route("path_route", r -> r.path("/get")
//						.uri("http://httpbin.org"))
//				.route("host_route", r -> r.host("*.daimabaike.com")
//						.uri("http://httpbin.org"))
//				.route("rewrite_route",
//						r -> r.host("*.rewrite.org")
//						.filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
//						.uri("http://httpbin.org"))
//				.route("hystrix_route",
//						r -> r.host("*.hystrix.org").filters(f -> f.hystrix(c -> c.setName("slowcmd")))
//								.uri("http://httpbin.org"))
//				.route("hystrix_fallback_route",
//						r -> r.host("*.hystrixfallback.org").filters(
//								f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
//								.uri("http://httpbin.org"))
//				.route("limit_route",
//						r -> r.host("*.limited.org").and().path("/anything/**")
//								.filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
//								.uri("http://httpbin.org"))

				.build();
		// @formatter:on
	}

//	@Bean
//	RedisRateLimiter redisRateLimiter() {
//		return new RedisRateLimiter(1, 2);
//	}
}

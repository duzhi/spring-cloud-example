package com.daimabaike.example.gateway;

import javax.xml.ws.ResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;

@SpringBootApplication
public class App {

	// String uri;

	public static void main(String[] args) {
		// org.springframework.jdbc.CannotGetJdbcConnectionException
		SpringApplication.run(App.class, args);
	}

	/**
	 * 代码先匹配
	 * 
	 * @param builder
	 * @return
	 */
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		 RewriteFunction<String, String>   d =  (exchange, s) -> Mono.just(SUCCESS_PREFIX+s+SUCCESS_SUFFIX);
		// @formatter:off
		return builder.routes()
				// 路径路由

//				 .route("path_route", r -> r.path("/test/**")
//				 .uri("lb://biz-bar"))
//					.route("path_route", r -> r.path("/**")
//							 .uri("lb://xx"))
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



//			    .route("rewrite_response_upper",
//			    		r -> r.path("/**")
//			    			.filters(f -> f.
//			    					modifyResponseBody(String.class, String.class,d)
//			    					)
//			                .uri("lb://biz-bar"))
				.build();
		// @formatter:on
	}

	private static final String SUCCESS_PREFIX = "{\"msg\":\"suczcess\",\"ret\":200,\"data\":";
	private static final String SUCCESS_SUFFIX = "}";

	// @Bean
	// RedisRateLimiter redisRateLimiter() {
	// return new RedisRateLimiter(1, 2);
	// }
//	@Bean
//	public ResponseResultHandler ResponseResultHandler1() {
//		return new ResponseResultHandler();
//	}
	
    @Autowired
    ServerCodecConfigurer        serverCodecConfigurer;
    @Autowired
    RequestedContentTypeResolver requestedContentTypeResolver;

    @Bean
    ResponseResultHandler responseWrapper() {
    	
    	System.out.println("serverCodecConfigurer="+serverCodecConfigurer);
    	
        return new ResponseResultHandler(serverCodecConfigurer
                .getWriters(), requestedContentTypeResolver);
    }

}

package com.gatewayserver.gatewayserver.configuration;

import java.time.LocalDateTime;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRouteLocator {
    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/hello/ac/**")
                        .filters(f -> f.rewritePath("/hello/accounts/(?<segment>.*)", "/${segment}")
                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                )
                        .uri("lb://ACCOUNTS")
                )
                .route(p -> p.path("/hello/loans/**")
                        .filters(f -> f.rewritePath("/hello/loans/(?<segment>.*)", "/${segment}")
                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                )
                        .uri("lb://LOANS"))
                .build();
    }
}

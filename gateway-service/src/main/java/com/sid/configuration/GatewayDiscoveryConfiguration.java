package com.sid.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayDiscoveryConfiguration {

    @Bean
    RouteLocator gatewayRoutesDiscovery(RouteLocatorBuilder builder) {
       return builder.routes()
                .route(r -> r.path("/customers/**").uri("lb://customer-service").id("r1"))
                .route(r -> r.path("/products/**").uri("lb://inventory-service").id("r2"))
                .build();
    }
}

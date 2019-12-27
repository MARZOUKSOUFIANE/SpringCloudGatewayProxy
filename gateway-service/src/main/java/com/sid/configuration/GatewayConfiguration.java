package com.sid.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
       return builder.routes()
                .route(r -> r.path("/customers/**").uri("http://localhost:8081/").id("r1"))
                .route(r -> r.path("/products/**").uri("http://localhost:8082/").id("r2"))
                .route(r->r.path("/restcountries/**")
                       .filters (f ->
                               f.addRequestHeader("x-rapidapi-host","restcountries-v1.p.rapidapi.com")
                               .addRequestHeader ("x-rapidapi-key","931d53cb56msh969165e0247eb2dp1552d8jsnbd84d6c04708")
                               .rewritePath("/restcountries/(?<segment>.*)","/${segment}")
                               .hystrix(h->h.setName("rest-countries").setFallbackUri("forward:/restCountriesFallback"))
                       )
                       .uri ("https://restcountries-v1.p.rapidapi.com").id("countries")
                       )
                .route(r->r.path("/muslimsalat/**")
                        .filters (f ->
                                f.addRequestHeader("x-rapidapi-host","muslimsalat.p.rapidapi.com")
                                .addRequestHeader ("x-rapidapi-key","931d53cb56msh969165e0247eb2dp1552d8jsnbd84d6c04708")
                                .rewritePath("/muslimsalat/(?<segment>.*)","/${segment}")
                                .hystrix(h->h.setName("muslimsalat").setFallbackUri("forward:/muslimsalatFallback"))
                        )
                        .uri ("https://muslimsalat.p.rapidapi.com").id("countries")
                )
                .build();
    }
}

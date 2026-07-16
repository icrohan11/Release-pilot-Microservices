package com.cts.releasepilot.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API Gateway - the single entry point for all clients.
 * Routes requests to downstream services (resolved from Eureka) and
 * validates the JWT via {@link com.cts.releasepilot.gateway.filter.JwtAuthenticationGlobalFilter}.
 */
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
        System.out.println("API gateway running successfully");
    }
}

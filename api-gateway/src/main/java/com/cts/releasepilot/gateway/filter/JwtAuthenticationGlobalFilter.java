package com.cts.releasepilot.gateway.filter;

import com.cts.releasepilot.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Global JWT validation filter. For every non-public route it:
 *  1. requires a "Authorization: Bearer &lt;token&gt;" header,
 *  2. verifies the token signature + expiry,
 *  3. injects X-Auth-User and X-Auth-Role headers so downstream services can
 *     rebuild the security context without re-parsing the token.
 * Requests without a valid token are rejected with 401.
 */
@Component
public class JwtAuthenticationGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationGlobalFilter.class);

    private final JwtUtil jwtUtil;

    @Value("${auth.header.user:X-Auth-User}")
    private String userHeader;

    @Value("${auth.header.role:X-Auth-Role}")
    private String roleHeader;

    /** Paths that do not require authentication. */
    private static final List<String> OPEN_PATHS = List.of(
            "/auth/login",
            "/auth/register",
            "/auth/logout",
            "/eureka",
            "/actuator",
            "/v3/api-docs",
            "/swagger-ui",
            "/api-docs"
    );

    public JwtAuthenticationGlobalFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        if (isOpen(path)) {
            log.debug("Gateway: open path, skipping JWT check -> {}", path);
            return chain.filter(exchange);
        }

        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Gateway: missing/invalid Authorization header for {}", path);
            return unauthorized(exchange, "Missing or malformed Authorization header");
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.isValid(token)) {
            log.warn("Gateway: invalid or expired JWT for {}", path);
            return unauthorized(exchange, "Invalid or expired token");
        }

        Claims claims = jwtUtil.parseClaims(token);
        String username = claims.getSubject();
        String role = claims.get("role", String.class);
        log.info("Gateway: authenticated user='{}' role='{}' -> {}", username, role, path);

        // Forward identity to downstream services via trusted headers
        ServerHttpRequest mutated = request.mutate()
                .header(userHeader, username == null ? "" : username)
                .header(roleHeader, role == null ? "" : role)
                .build();

        return chain.filter(exchange.mutate().request(mutated).build());
    }

    private boolean isOpen(String path) {
        return OPEN_PATHS.stream().anyMatch(path::startsWith);
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().add("X-Auth-Error", message);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        // Run early, before routing
        return -1;
    }
}

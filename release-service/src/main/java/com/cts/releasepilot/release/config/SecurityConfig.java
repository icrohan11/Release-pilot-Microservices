package com.cts.releasepilot.release.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Stateless security for the release-service.
 * <ul>
 *   <li>Docs and actuator endpoints are public.</li>
 *   <li>All business endpoints trust the gateway-injected identity headers,
 *       parsed by {@link RoleBasedHeaderFilter}, and are protected by role.</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public RoleBasedHeaderFilter roleBasedHeaderFilter() {
        return new RoleBasedHeaderFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/actuator/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/api-docs/**",
                        "/v3/api-docs/**",
                        "/error"
                ).permitAll()
                .requestMatchers("/releases/**", "/patches/**", "/environments/**",
                        "/promotions/**", "/freezes/**")
                        .hasAnyRole("ReleaseManager", "DevLead", "QAEngineer", "Admin", "ProductManager")
                .anyRequest().authenticated()
            )
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(roleBasedHeaderFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

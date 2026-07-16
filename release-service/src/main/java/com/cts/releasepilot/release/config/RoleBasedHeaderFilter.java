package com.cts.releasepilot.release.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Rebuilds the Spring Security context from the trusted identity headers that
 * the API gateway injects after validating the JWT
 * (X-Auth-User + X-Auth-Role). This keeps each downstream service stateless -
 * it never re-parses the token or hits the user database.
 */
public class RoleBasedHeaderFilter extends OncePerRequestFilter {

    @Value("${auth.header.user:X-Auth-User}")
    private String userHeader;

    @Value("${auth.header.role:X-Auth-Role}")
    private String roleHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String username = request.getHeader(userHeader);
        String role = request.getHeader(roleHeader);

        if (StringUtils.hasText(username) && StringUtils.hasText(role)
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            var authority = new SimpleGrantedAuthority("ROLE_" + role);
            var authentication = new UsernamePasswordAuthenticationToken(
                    username, null, List.of(authority));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}

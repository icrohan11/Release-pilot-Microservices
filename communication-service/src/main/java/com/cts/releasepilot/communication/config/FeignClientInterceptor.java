package com.cts.releasepilot.communication.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Propagates the caller's identity (and Authorization token) onto outgoing Feign
 * calls so downstream services can still authorise the request.
 */
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Value("${auth.header.user:X-Auth-User}")
    private String userHeader;

    @Value("${auth.header.role:X-Auth-Role}")
    private String roleHeader;

    @Override
    public void apply(RequestTemplate template) {
        var attrs = RequestContextHolder.getRequestAttributes();
        if (attrs instanceof ServletRequestAttributes servletAttrs) {
            var request = servletAttrs.getRequest();
            copyHeader(template, request.getHeader("Authorization"), "Authorization");
            copyHeader(template, request.getHeader(userHeader), userHeader);
            copyHeader(template, request.getHeader(roleHeader), roleHeader);
        }
    }

    private void copyHeader(RequestTemplate template, String value, String name) {
        if (value != null && !value.isBlank()) {
            template.header(name, value);
        }
    }
}

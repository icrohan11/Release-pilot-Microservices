package com.cts.releasepilot.communication.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign defaults: full request/response logging. The identity-propagating
 * {@link FeignClientInterceptor} is a {@code @Component} and is picked up
 * automatically by Feign.
 */
@Configuration
public class FeignClientConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}

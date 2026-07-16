package com.cts.releasepilot.release;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Release Service - Release Management.
 * Manages release packages, patch dependencies, environments, promotion
 * requests and change freeze windows across the product lifecycle.
 */
@SpringBootApplication
@EnableFeignClients
public class ReleaseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReleaseServiceApplication.class, args);
    }
}

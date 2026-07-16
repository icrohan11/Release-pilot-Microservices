package com.cts.releasepilot.communication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Communication Service - release notes, customer advisories and user notifications.
 * Consumes release-service via Feign to validate releases before publishing notes.
 */
@SpringBootApplication
@EnableFeignClients
public class CommunicationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunicationServiceApplication.class, args);
        System.out.println("Communication is working successfully");
    }
}

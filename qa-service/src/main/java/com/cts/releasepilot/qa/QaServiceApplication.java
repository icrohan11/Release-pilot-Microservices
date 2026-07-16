package com.cts.releasepilot.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * QA Service - Quality Assurance.
 * Manages test suites, test executions and release quality gates.
 */
@SpringBootApplication
@EnableFeignClients
public class QaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QaServiceApplication.class, args);
        System.out.println("QA is working successfully");
    }
}

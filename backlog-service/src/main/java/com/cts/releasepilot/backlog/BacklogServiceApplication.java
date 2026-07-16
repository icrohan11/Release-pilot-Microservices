package com.cts.releasepilot.backlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Backlog Service - Backlog &amp; Sprint Management.
 * Manages backlog items, sprints and sprint items for agile release planning.
 */
@SpringBootApplication
@EnableFeignClients
public class BacklogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BacklogServiceApplication.class, args);
        System.out.println("Backlog is working successfully");
    }
}

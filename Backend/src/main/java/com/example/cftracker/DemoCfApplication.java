package com.example.cftracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoCfApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCfApplication.class, args);
        System.out.println("Server Started");
    }
}
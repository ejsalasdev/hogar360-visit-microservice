package com.powerup.visitmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VisitMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisitMicroserviceApplication.class, args);
    }

}

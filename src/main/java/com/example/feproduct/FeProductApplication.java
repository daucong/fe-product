package com.example.feproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FeProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeProductApplication.class, args);
    }

}

package com.mykart.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MyKartServiceDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyKartServiceDiscoveryApplication.class, args);
    }

}

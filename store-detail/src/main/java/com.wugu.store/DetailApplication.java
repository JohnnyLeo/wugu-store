package com.wugu.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DetailApplication {
    public static void main(String[] args) {
        SpringApplication.run(DetailApplication.class, args);
    }
}

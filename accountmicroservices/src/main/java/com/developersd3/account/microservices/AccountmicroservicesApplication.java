package com.developersd3.account.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccountmicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountmicroservicesApplication.class, args);
    }

}

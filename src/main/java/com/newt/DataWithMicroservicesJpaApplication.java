package com.newt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.newt")
@EnableDiscoveryClient
@EnableEurekaClient
@EnableSwagger2

public class DataWithMicroservicesJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataWithMicroservicesJpaApplication.class, args);
	}
	
}


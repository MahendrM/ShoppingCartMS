package com.newt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableSwagger2
@EnableAutoConfiguration
public class DataWithMicroservicesJpaApplication {

	public static void main(String[] args) {
		try {

			ConfigurableApplicationContext context = SpringApplication.run(DataWithMicroservicesJpaApplication.class,
					args);
			context.getBean(KafkaController.class).consumeNumbersList();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.newt.shoppingcart.controller")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Delivery Microservice",
				"Provides services to delivery products and order of  status update", "V.1.0", "Terms of service",
				"devopsinabox@newtglobal.com", "", "");
		return apiInfo;
	}
}

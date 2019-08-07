package com.spring.parent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringEurekaSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaSecondApplication.class, args);
	}

}

package com.spring.parent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
//@ComponentScan("com.spring.service")
public class SpringEurekaFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaFirstApplication.class, args);
	}

}

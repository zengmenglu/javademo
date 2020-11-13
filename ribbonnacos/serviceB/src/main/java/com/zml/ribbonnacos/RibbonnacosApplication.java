package com.zml.ribbonnacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RibbonnacosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonnacosApplication.class, args);
	}

}

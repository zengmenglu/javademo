package com.zml.ribbonnacos;

import com.zml.ribbonConfig.ChooseFirstRule;
import com.zml.ribbonConfig.DefaultRule;
import com.zml.ribbonConfig.NacosClusterRule;
import com.zml.ribbonConfig.NacosWeightRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
@RibbonClients({
		@RibbonClient(name="serviceB",configuration = DefaultRule.class),
		@RibbonClient(name="serviceA",configuration = ChooseFirstRule.class),})
public class RibbonnacosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonnacosApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}

package com.zml.ribbonConfig;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DefaultRule {
    // 使用随机策略
    @Bean
    public AbstractLoadBalancerRule ribbonRule(){ // IRule
        return new RandomRule();
    }

    @Bean
    public IPing ping(){
        System.out.println("Ping......");

        return new PingUrl();
    }

}

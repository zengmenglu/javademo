package com.zml.ribbonConfig;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NacosWeightRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig){

    }

    @Override
    public Server choose(Object o){
        try {
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            String name = loadBalancer.getName(); // 想要请求的微服务的名称
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();   // 拿到服务发现的相关API
            Instance instance = namingService.selectOneHealthyInstance(name); // nacos client自动通过基于权重的负载均衡算法，给我们选择一个实例。
            System.out.println("nacos choose：port:"+instance.getPort()+",instance:"+ instance);
            return new NacosServer(instance);
        } catch (NacosException e) {
            return null;
        }
    }
}

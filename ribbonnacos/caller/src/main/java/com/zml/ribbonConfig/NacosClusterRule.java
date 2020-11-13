package com.zml.ribbonConfig;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.ExtendBalancer;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerStats;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NacosClusterRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        try {
            String clusterName = nacosDiscoveryProperties.getClusterName();// 拿到配置文件中的集群名称
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            String name = loadBalancer.getName(); // 想要请求的微服务的名称
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();// 拿到服务发现的相关API

            LoadBalancerStats stats = loadBalancer.getLoadBalancerStats();
            System.out.println("stats:"+stats.toString());

            // 1. 找到指定服务的所有实例 A
            List<Instance> instances = namingService.selectInstances(name, true);

            // 2. 过滤出相同集群下的所有实例 B
            List<Instance> sameClusterInstances = instances.stream()
                    .filter(instance -> Objects.equals(instance.getClusterName(), clusterName))
                    .collect(Collectors.toList());

            // 3. 如果B是空，就用A
            List<Instance> instancesToBeChosen = new ArrayList<>();
            if (CollectionUtils.isEmpty(sameClusterInstances)) {
                instancesToBeChosen = instances;
                System.out.println("发生跨集群的调用, name:"+name+",clusterName:"+clusterName+",instances:"+ instances);
            } else {
                instancesToBeChosen = sameClusterInstances;
            }

            // 4. 基于权重的负载均衡算法，返回1个实例
            Instance instance = ExtendBalancer.getHostByRandomWeight2(instancesToBeChosen);
            System.out.println("nacos choose port:"+instance.getPort()+",instance:"+instance);

            return new NacosServer(instance);
        } catch (NacosException e) {
            System.out.println("异常:"+ e);
            return null;
        }
    }
}

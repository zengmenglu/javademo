package com.zml.ribbonConfig;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration
public class ChooseFirstRule extends AbstractLoadBalancerRule implements IPing {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }

    @Override
    public Server choose(Object o) {
        System.out.println("key:" + o);
        List<Server> allServers = getLoadBalancer().getAllServers();
        System.out.println(allServers.toString()+", ***choose:"+allServers.get(0).getHostPort());
        return allServers.get(0);
    }

    public  boolean isAlive(Server server){ // 默认30s执行2次
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date().getTime())+" isAlive"+server.getHostPort());
        return true;
    }
}
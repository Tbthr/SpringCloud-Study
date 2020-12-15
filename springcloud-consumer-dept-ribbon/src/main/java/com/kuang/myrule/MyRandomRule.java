package com.kuang.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
// 每个服务，访问5次，换下一个服务
public class MyRandomRule extends AbstractLoadBalancerRule {
// 放在主程序的上级目录中，仅供该服务使用
// 若与主程序同级，则共享于所有服务

// IRule：
//      RoundRobinRule 轮询
//      RandomRule 随机
//      AvailabilityFilteringRule 会先过滤掉 访问故障的服务（跳闸），对剩下的进行轮询
//      RetryRule 会先按照轮询获取服务。如果服务获取失败，则会在指定的时间内进行重试

    private int total = 0; //被调用的次数
    private int currentIndex = 0; //当前是谁在提供服务~

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server;

        while (true) {

            if (Thread.interrupted()) {
                return null;
            }

            List<Server> upList = lb.getReachableServers(); //获得活着的服务
            List<Server> allList = lb.getAllServers(); //获得全部的服务

            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }

            if (total < 5) {
                server = upList.get(currentIndex);
                total++;
            } else {
                total = 0;
                currentIndex++;
                if (currentIndex >= upList.size()) {
                    currentIndex = 0;
                }
                server = upList.get(currentIndex); //从活着的服务中，获取指定的服务来进行操作
            }

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            Thread.yield();
        }
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub
    }
}

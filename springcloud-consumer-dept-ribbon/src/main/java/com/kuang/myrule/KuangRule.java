package com.kuang.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 放在主程序的上级目录中，仅供该服务使用；若与主程序同级，则共享于所有服务
public class KuangRule {

    // IRule：
    // RoundRobinRule 轮询
    // RandomRule 随机
    // AvailabilityFilteringRule：会先过滤掉，访问故障的服务（跳闸），对剩下的进行轮询~
    // RetryRule：会先按照轮询获取服务~，如果服务获取失败，则会在指定的时间内进行重试

    @Bean
    public IRule myRule() {
        return new RoundRobinRule(); //默认是轮询，现在我们定义为~ KuangRandomRule
    }

}

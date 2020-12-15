package com.kuang.springcloud;

import com.kuang.myrule.MyRandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient // 客户端
//在微服务启动的时候就能去加载我们自定义Ribbon类
// (name=服务名称,configuration=自定义算法)
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT", configuration = MyRandomRule.class)
public class DeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80.class, args);
    }
}

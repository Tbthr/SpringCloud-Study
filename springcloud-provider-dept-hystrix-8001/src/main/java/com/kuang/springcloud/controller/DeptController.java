package com.kuang.springcloud.controller;

import com.kuang.springcloud.pojo.Dept;
import com.kuang.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DeptController {
    @Resource
    private DeptService deptService;

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet") //熔断机制，调用失败时调用hystrixGet
//            , commandProperties = {
//            // 开启断路器
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            // 请求次数
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            // 时间窗口期
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//            // 失败率
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")}
//    ) //更多查看：HystrixCommandProperties
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);

        if (dept == null) {
            throw new RuntimeException("id=>" + id + "，不存在该用户，或者信息无法找到~");
        }

        return dept;
    }

    //备选方法
    public Dept hystrixGet(@PathVariable("id") Long id) {
        return new Dept()
                .setDeptno(id)
                .setDname("id=>" + id + "没有对应的信息，null--@Hystrix")
                .setDb_source("no this database in MySQL");
    }
}

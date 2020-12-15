package com.kuang.springcloud.controller;

import com.kuang.springcloud.pojo.Dept;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeptConsumerController {

    // 理解：消费者，不应该有service层~
    // RestTemplate .... 供我们直接调用就可以了！ 注册到Spring中
    // (url, 实体：Map ,Class<T> responseType)
    @Resource
    private RestTemplate restTemplate; //提供多种便捷访问远程http服务的方法，简单的Restful服务模板~

    //Ribbon 我们这里的地址，应该是一个变量，通过服务名来访问
    //Ribbon 和 Eureka 整合以后，客户端可以直接调用，不用关心IP地址和端口号~
//     private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

}

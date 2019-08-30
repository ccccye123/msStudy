package org.ccccye.movie.controller;

import org.ccccye.movie.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;

import java.util.List;

@RestController
public class MovieController {
    private static final Logger Logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate RestTemplate;
    @Autowired
    private DiscoveryClient DiscoveryClient;
    @Autowired
    private LoadBalancerClient LoadBalancerClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        return RestTemplate.getForObject("http://localhost:8091/"+id, User.class);
    }

    @GetMapping("/userinfo")
    public List<ServiceInstance> showInfo(){
        return DiscoveryClient.getInstances("microservice-provider-user");
    }

    @GetMapping("/getinstance")
    public void LogInstance(){
        ServiceInstance ServiceInstance = this.LoadBalancerClient.choose("microservice-provider-user");

        MovieController.Logger.info("{}:{}:{}",
                ServiceInstance.getInstanceId(), ServiceInstance.getHost(), ServiceInstance.getPort());
    }
}

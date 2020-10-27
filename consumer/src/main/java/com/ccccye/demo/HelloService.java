package com.ccccye.demo;

import com.ccccye.demo.api.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate RestTemplate;

    @Autowired
    private UserApiService userApiService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/logInstance")
    public void logInstance(){
        ServiceInstance instance = loadBalancerClient.choose("microservice-provider-user");
        System.out.printf("%s\r\n",instance.getInstanceId());
    }

    public String getUser(String userid){
        return userApiService.getUser(userid).toString();
//        return RestTemplate.getForObject("http://MICROSERVICE-PROVIDER-USER/" + userid, String.class);
    }
}

package org.ccccye.movie.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.ccccye.movie.feign.TestFeign;
import org.ccccye.movie.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;

import java.util.List;

//@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {
    private static final Logger Logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate RestTemplate;
    @Autowired
    private DiscoveryClient DiscoveryClient;
    @Autowired
    private LoadBalancerClient LoadBalancerClient;
    @Autowired
    private TestFeign testFeign;

//    private TestFeign userFeignClient;
//    private TestFeign adminFeignClient;

//    @Autowired
//    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract){
//        this.userFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder)
//                .requestInterceptor(
//                        new BasicAuthRequestInterceptor("user", "password")
//                ).target(TestFeign.class, "http://microservice-provider-user/");
//
//        this.adminFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder)
//                .requestInterceptor(
//                        new BasicAuthRequestInterceptor("admin", "password")
//                ).target(TestFeign.class, "http://microservice-provider-user/");
//    }

    //@HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        return testFeign.findById(id);
    }

    /**
     * findById的回退方法
     * @param id
     * @return
     */
    public User findByIdFallback(Long id){
        User user = new User();
        user.setId(-1L);
        user.setUsername("默认用户");
        user.setAge(-1);
        user.setName("default");
        return user;
    }

//    @GetMapping("/user/{id}")
//    public User findByIdUser(@PathVariable Long id){
//        return userFeignClient.findById(id);
//    }
//
//    @GetMapping("/admin/{id}")
//    public User findByIdAdmin(@PathVariable Long id){
//        return adminFeignClient.findById(id);
//    }

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

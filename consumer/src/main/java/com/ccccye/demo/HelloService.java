package com.ccccye.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate RestTemplate;

    public String hiService(String name){
        return RestTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }
}

package com.ccccye.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HiController {
    @Autowired
    HelloService HelloService;

    @GetMapping(value = "/logInstance")
    public void getInstance(){
        HelloService.logInstance();
    }

    @GetMapping(value = "/user/{userid}")
    public String getUser(@PathVariable String userid){
        return HelloService.getUser(userid);
    }
}

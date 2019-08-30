package com.ccccye.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    HelloService HelloService;

    @RequestMapping(value = "/hi")
    public String Hi(@RequestParam String name){
        return HelloService.hiService(name);
    }
}

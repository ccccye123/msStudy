package org.ccccye.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
    @Value("${profile}")
    private String profile;

    @Autowired
    private Environment environment;

    @GetMapping(value = "/profile")
    public String prfile(){
        //String profile = environment.getProperty("profile");
        return profile;
    }
}

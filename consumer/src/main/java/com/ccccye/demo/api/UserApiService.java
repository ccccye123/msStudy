package com.ccccye.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-provider-user")
public interface UserApiService {

    @GetMapping(value = "/{userid}")
    public Object getUser(@PathVariable String userid);
}

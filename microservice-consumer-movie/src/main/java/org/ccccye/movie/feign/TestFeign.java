package org.ccccye.movie.feign;


import feign.Param;
import feign.RequestLine;
import org.ccccye.config.movie.FeignConfiguration;
import org.ccccye.config.movie.FeignLogConfiguration;
import org.ccccye.config.movie.FooConfiguration;
import org.ccccye.movie.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// name 表示访问的服务名称
// url 可以指定url来访问服务提供者
@FeignClient(name = "microservice-provider-user", configuration = {FeignLogConfiguration.class, FooConfiguration.class})
//@FeignClient(name = "microservice-provider-user")
public interface TestFeign {

    //    @RequestLine("GET /{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);
}

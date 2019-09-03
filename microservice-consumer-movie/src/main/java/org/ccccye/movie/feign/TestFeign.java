package org.ccccye.movie.feign;


import feign.Param;
import feign.RequestLine;
import org.ccccye.config.movie.FeignConfiguration;
import org.ccccye.config.movie.FooConfiguration;
import org.ccccye.movie.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;

// name 表示访问的服务名称
// url 可以指定url来访问服务提供者
@FeignClient(name = "microservice-provider-user", configuration = {FeignConfiguration.class, FooConfiguration.class})
public interface TestFeign {

    //@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @RequestLine("GET /{id}")
    public User findById(@Param("id") Long id);
}

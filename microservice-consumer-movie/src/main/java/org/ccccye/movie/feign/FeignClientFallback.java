package org.ccccye.movie.feign;

import org.ccccye.movie.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignClientFallback implements TestFeign{
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(FeignClientFallback.class);

    @Override
    public User findById(Long id) {

        User user = new User();
        user.setId(-1L);
        user.setUsername("默认用户");
        user.setAge(-1);
        user.setName("default");
        return user;
    }
}

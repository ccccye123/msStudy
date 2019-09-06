package org.ccccye.config.movie;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FeignLogConfiguration {

    @Bean
    Logger.Level feignLoggerLevel(){
        // 打印日志的详细程度
        return Logger.Level.BASIC;
    }
}

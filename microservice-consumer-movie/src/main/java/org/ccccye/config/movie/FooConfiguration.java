package org.ccccye.config.movie;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooConfiguration {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user", "password");
    }
}

package org.ccccye.config.movie;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    /**
     * 使用feign原声契约,就可以使用feign原生注解了
     * @return
     */
    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }
}

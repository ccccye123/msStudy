package org.ccccye.config.movie;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {

    @Bean
    public IRule ribbonRule(){
        // 负载均衡规则为随机
        return new RandomRule();
    }
}

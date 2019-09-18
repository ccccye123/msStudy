# 常用命令
nsenter --target 上进程id --mount --uts --ipc --net --pid  #输入该命令便进入到容器中

# 版本差异
书使用的spring cloud版本是Camden SR4,我用的是Greenwich SR2
## feign的差异
新Greenwich版已经把 spring-cloud-starter-feign换成spring-cloud-starter-openfeign了
## secrity配置的问题
书上关于eureka配置简单加密的配置有个问题，直接从 secrity级开始配置的，造成无法自定义配置用户名密码。
正确的配置第一级应当是 spring才到secrity，如
```yaml
spring:
  application:
    name: microservice-discovery-eureka-ha
  security:
    basic:
      enabled: true
    user:
      name: user
      password: password123
```
## eureka配置了加密后服务无法访问
原因为spring boot 2.x以后增加了csrf,关闭即可.在eureka项目增加配置类
```java
@Configuration
public class SecurityCofnig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        super.configure(http);
    }
}

```
[参考链接](https://www.cnblogs.com/whm-blog/p/10847139.html)

## hystrix.stream无法访问解决方法
增加配置参数
```yaml
management:
  endpoints:
    web:
      exposure:
        include: hystrix.stream
```
[参考链接](https://windmt.com/2018/04/16/spring-cloud-5-hystrix-dashboard/)

## zuul的routes无法访问
新版的actuator默认没有暴露routes管理端点，需要改配合文件启用，其他被隐藏的管理页面也可以使用一样的方式启用
```yaml
management:
  endpoints:
    web:
      exposure:
        include: routes
```
[参考链接](https://blog.csdn.net/wuliu_cui/article/details/86578890)
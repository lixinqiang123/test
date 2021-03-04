package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient  //eurekaclient  声明是注册中心的客户端）
public class RibbonApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(RibbonApp.class,args);
    }


    @Bean
    @LoadBalanced
        //声明 ribbon （并对resttemmplate进行负载均衡   调用的时候直接写服务名就行  不用写ip和端口）
    RestTemplate restTemplate() {
        return new RestTemplate();// RestTemplate对httpclient发送请求 进行封装了
    }

}

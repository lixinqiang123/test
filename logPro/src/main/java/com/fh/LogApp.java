package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
                                //不配置数据库
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  //声明是启动类
@EnableEurekaClient//声明是客户端
public class LogApp
{
    public static void main( String[] args )
    {
        //方法
        SpringApplication.run(LogApp.class,args);
    }
}

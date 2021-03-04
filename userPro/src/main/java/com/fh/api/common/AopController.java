/*package com.fh.api.common;


import com.fh.api.entity.po.MongoLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AopController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request;

    //前置通知
    @Before("execution(* com.fh.api.controller.*.*(..))")
    public void aopLog(JoinPoint joinPoint){

        //获取签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();


        String requestURI = request.getRequestURI();

        Map map = new HashMap();

        map.put("name","222");
        map.put("content",requestURI);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost:8085/api/log/addMongoLog", map, String.class);

        System.out.println("数据"+stringResponseEntity);

    }
}*/

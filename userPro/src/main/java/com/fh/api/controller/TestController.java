package com.fh.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试方法
 *
 */


@RestController
@CrossOrigin
@RequestMapping("api/test/")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("getTest")
    public String getTest(){

        return "555";
    }

    @RequestMapping("test")
    public String test(){

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8085/api/log/queryLogAll", String.class);

        System.out.println(forEntity);
        return "success";
    }


    /**
     *
     * 测试新增接口
     */
    @RequestMapping("testAdd")
    public Object testAdd(String name,String author){

        Map map = new HashMap();
        map.put("name",name);
        map.put("author",author);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost:8085/api/log/addMongoLog",map, String.class);

        System.out.println(stringResponseEntity);
        return stringResponseEntity;
    }

    /**
     * 测试分页查询的接口
     *
     *
     */

    @RequestMapping("testQuery")
    public Object testQuery(){

        Map map = new HashMap();

        map.put("page",1);
        map.put("size",5);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost:8085/api/log/queryPage", map, String.class);

        return stringResponseEntity;
    }

    @RequestMapping("selectLog")
    public Object selectLog(){


        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8085/api/log/selectAll", String.class);

        return forEntity;
    }

}

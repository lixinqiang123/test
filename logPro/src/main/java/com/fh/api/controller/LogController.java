package com.fh.api.controller;


import com.fh.api.entity.po.MongoLog;
import com.fh.api.entity.vo.Params;
import com.fh.api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/log/")
public class LogController {

    @Autowired
    private LogService logService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("getData2")
    public String getData2(){

        ServiceInstance choose = loadBalancerClient.choose("USERSERVER");

        String url="http://"+choose.getHost()+":"+choose.getPort();

        return url;
    }


    /**
     * 返回对象
     *
     */
    @GetMapping("selectAll")
    public Object selectAll(){

        List<MongoLog> mongoLogList = logService.queryLogAll();

        return mongoLogList;
    }


    /**
     *查询全部
     */

    @GetMapping("queryLogAll")
    public Map queryLogAll(){

        //创建map集合
        Map map = new HashMap();
        List<MongoLog> mongoLogList=logService.queryLog();

        map.put("data",mongoLogList);
        return map;
    }


    /**
     *
     * 分页查询
     *
     *
     */
    @PostMapping("queryPage")
    public Map queryPage(@RequestBody Params params){

       Map pagelog=logService.queryLogPage(params);

        return pagelog;
    }


    /**
     *
     * 修改
     *
     */

    @PostMapping("updateMongoLog")
    public String updateLog(MongoLog mongoLog){

        logService.updateLog(mongoLog);

        return "success";
    }

    /**
     *
     * 新增
     */
    @PostMapping("addMongoLog")
    public String addMongoLog(@RequestBody  MongoLog mongoLog){
        logService.addMongoLog(mongoLog);
        return "success";
    }

    /**
     * 测试方法
     *
     */
    @RequestMapping("testAdd")
    public String testAdd(String name,Integer age){

        return "我叫"+name+"年龄"+age;
    }
}

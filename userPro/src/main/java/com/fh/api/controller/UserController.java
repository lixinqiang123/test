package com.fh.api.controller;


import com.fh.api.entity.po.User;
import com.fh.api.entity.vo.Params;
import com.fh.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController  //类上默认加注解@Controller 每个方法加 @ResponseBody
@CrossOrigin
@RequestMapping("api/user/") //提供一个访问路径
public class UserController {


    /**
     *
     */

    @Autowired
    private UserService userService;

    @PostMapping("addUser")
    public Map addUser(User user){

        //创建map集合
        Map map = new HashMap();
        user.setCreateDate(new Date());

        userService.addUser(user);

        map.put("success","新增成功");

        return map;
    }


    /**
     *
     */

    @DeleteMapping("deleteUser")
    public Map deleteUser(Integer id){

        Map map = new HashMap();

        userService.deleteUser(id);
        map.put("success","删除成功");

        return map;
    }


    @PostMapping("queryUser")
    public Map queryUser(@RequestBody Params params){

        Map user=userService.queryUser(params);

        return user;
    }


    /**
     * 测试httpclient
     *
     */

    @RequestMapping(value = "testadd",method = RequestMethod.POST)
    public String testadd(@RequestBody User user,Integer flag,String message){

        return "名字是"+user.getName()+"flag"+flag+"信息"+message;
    }
}

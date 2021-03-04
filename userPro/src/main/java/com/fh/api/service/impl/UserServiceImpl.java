package com.fh.api.service.impl;

import com.fh.api.dao.UserDao;
import com.fh.api.entity.po.User;
import com.fh.api.entity.vo.Params;
import com.fh.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service   //声明业务逻辑处理层
public class UserServiceImpl  implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {

        userDao.addUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public Map queryUser(Params params) {

        Map map = new HashMap();
        //查询总条数
        Long count=userDao.queryCount(params);

        //计算起始页数  页数减一 * 每页的条数
        Integer startindex=(params.getPage()-1)*params.getSize();

        //赋值
        params.setStartIndex(startindex);

        //查询数据
        List<User> users=userDao.queryUser(params);

        map.put("count",count);
        map.put("data",users);

        return map;
    }
}

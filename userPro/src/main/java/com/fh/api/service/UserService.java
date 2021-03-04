package com.fh.api.service;

import com.fh.api.entity.po.User;
import com.fh.api.entity.vo.Params;

import java.util.Map;

public interface UserService {
    void addUser(User user);

    void deleteUser(Integer id);

    Map queryUser(Params params);
}

package com.fh.api.dao;


import com.fh.api.entity.po.User;
import com.fh.api.entity.vo.Params;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    @Insert("insert into user (name,realname,password,img,birthday,weight,createDate)value(#{name}," +
            "#{realname},#{password},#{img},#{birthday},#{weight},#{createDate})")
    void addUser(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);

    Long queryCount(Params params);

    List<User> queryUser(Params params);
}

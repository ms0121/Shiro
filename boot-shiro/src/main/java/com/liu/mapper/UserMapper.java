package com.liu.mapper;

import com.liu.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public void save(User user);

    public User findByUserName(String name);

}

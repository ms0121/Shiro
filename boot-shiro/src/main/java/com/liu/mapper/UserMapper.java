package com.liu.mapper;

import com.liu.entity.Role;
import com.liu.entity.User;
import com.liu.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public void save(User user);

    public User findByUserName(String name);

    // 通过用户名查找所拥有的权限
    public List<Role> findRoleByUserName(String name);
}

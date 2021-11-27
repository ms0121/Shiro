package com.liu.service;

import com.liu.entity.Perms;
import com.liu.entity.Role;
import com.liu.entity.User;
import com.liu.query.UserQuery;

import java.util.List;

public interface UserService {
    public void save(User user);
    public User findByUserName(String name);
    public User findRoleByUserName(String name);
    // 查询角色下所有的权限
    public List<Perms> findPermsByRoleId(Integer id);

}

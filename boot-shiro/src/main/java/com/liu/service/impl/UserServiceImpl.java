package com.liu.service.impl;

import com.liu.entity.Role;
import com.liu.entity.User;
import com.liu.mapper.UserMapper;
import com.liu.query.UserQuery;
import com.liu.service.UserService;
import com.liu.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        // 处理业务，调用mapper接口实现用户的注册
        // 获取一个指定位数的随即salt
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        // 进行md5加密
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        // 将加密后的密码设置在注册用户的密码上
        user.setPassword(md5Hash.toHex());
        // 保存当前注册的用户信息
        userMapper.save(user);
    }

    @Override
    public User findByUserName(String name) {
        return userMapper.findByUserName(name);
    }

    @Override
    public List<Role> findRoleByUserName(String name) {
        List<Role> roles = userMapper.findRoleByUserName(name);
        System.out.println("user = " + roles);
        return roles;
    }

}

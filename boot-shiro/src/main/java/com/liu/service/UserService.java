package com.liu.service;

import com.liu.entity.User;

public interface UserService {
    public void save(User user);
    public User findByUserName(String name);
}

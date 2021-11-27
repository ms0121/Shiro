package com.liu.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.SQLException;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private SqlSession sqlSession;

    @RequestMapping("save")
    @RequiresRoles(value = {"admin"})  // 表示该方法只能是有admin角色的用户才能进行访问
    @ResponseBody
    public String save(){
        System.out.println("进入到方法体.......");
        return "执行成功";
    }

    @RequestMapping("get")
    @ResponseBody
    public void get() throws SQLException {
        Connection connection = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
        System.out.println("connection = " + connection);
    }




}

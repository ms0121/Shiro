package com.liu.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("order")
public class OrderController {


    @RequestMapping("save")
    @RequiresRoles(value = {"admin"})  // 表示该方法只能是有admin角色的用户才能进行访问
    @ResponseBody
    public String save(){
        System.out.println("进入到方法体.......");
        return "执行成功";
    }


}

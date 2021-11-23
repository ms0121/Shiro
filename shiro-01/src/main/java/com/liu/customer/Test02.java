package com.liu.customer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class Test02 {
    public static void main(String[] args) {
        // 创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 设置自定义的域
        securityManager.setRealm(new CustomerRealm());
        // 该安全安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        // 登录用户生成token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "1234");

        try {
            // 验证登录
            subject.login(usernamePasswordToken);
            System.out.println("登录成功 = " + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            System.out.println("账号不存在，请重新登录!");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误，请重新输入!");
            e.printStackTrace();
        } finally {
            System.out.println("执行完毕");
        }
    }
}

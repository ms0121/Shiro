package com.liu.md5;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

public class CustomerTest {
    public static void main(String[] args) {
        // 获取安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 将自定义的域设置到安全管理器中
        // securityManager.setRealm(new CustomerMd5Realm());
        // 告知realm使用的是哪个md5加密算法(哈希凭证匹配器)
        CustomerMd5Realm realm = new CustomerMd5Realm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置使用的算法
        matcher.setHashAlgorithmName("md5");
        // 告知散列的次数（如果使用第三种方法）
        matcher.setHashIterations(1024);

        realm.setCredentialsMatcher(matcher);
        // 将域设置到安全管理器中
        securityManager.setRealm(realm);

        // 将安全管理器设置到安全工具类中
        SecurityUtils.setSecurityManager(securityManager);
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();

        // 生成登录用户的token
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");

        // 认证部分
        try {
            // 验证是否正确
            subject.login(token);
            System.out.println("登录成功!");
        } catch (UnknownAccountException e) {
            System.out.println("账号不存在!");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            System.out.println("密码不正确，请重新输入!");
            e.printStackTrace();
        }
        System.out.println("=========================");

        // 认证之后进入授权部分
        if (subject.isAuthenticated()) {

//            基于角色的访问控制
            // 判断当前登录的用户是否有admin的权限
//            boolean hasRole = subject.hasRole("admin");
//            boolean hasRole1 = subject.hasRole("user");
//            System.out.println("hasRole = " + hasRole);
//            System.out.println("hasRole1 = " + hasRole1);

            // 判断当前的登录用户是否同时有多个角色
            // System.out.println(subject.hasAllRoles(Arrays.asList("admin", "user")));

            // 判断当前的登录用户是否有其中的某一个角色(有哪个角色就会返回那个为true)
//            boolean[] flag = subject.hasRoles(Arrays.asList("admin", "user"));
//            for (boolean b : flag) {
//                System.out.println(b);
//            }


//            基于资源的访问控制
//            基于权限字符串的访问控制。资源标识符：操作：资源类型
            System.out.println("只对01用户有修改权限 = " + subject.isPermitted("user:update:01"));
            System.out.println("对所有的商品有所有的操作权限: " + subject.isPermitted("product:*:*"));

            for (boolean b : subject.isPermitted("user:update:01", "product:create:01")) {
                System.out.println("是否拥有权限 = " + b);
            }

        }
    }
}

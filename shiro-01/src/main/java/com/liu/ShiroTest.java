package com.liu;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 *
 */
public class ShiroTest {
    public static void main(String[] args) {
        // 1.创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 2.给安全管理器设置Realm，可以理解为要去哪里进行验证用户信息
        // 也就是说目前登录的用户是zhangsan，那么它就要去 shiro.ini里面查找看看是否存在该用户（包括了认证授权的相关代码）
        IniRealm realm = new IniRealm("classpath:shiro.ini");
        // 将配置文件中的数据设置在安全管理器中
        securityManager.setRealm(realm);

        // 3.给全局工具类设置全局管理器
        SecurityUtils.setSecurityManager(securityManager);
        // 4.获取主体对象（外部应用与subject进行交互）：即subject记录了当前登录的用户信息
        // subject包含认证的信息，只需要将登录用户生成的token进行验证即可
        Subject subject = SecurityUtils.getSubject();
        // 5.创建安全令牌token(使用当前登录用户的信息进行生成)
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "123");
        // 设置记住我（即下次再进行登录的时候就不再需要重新填写了）
        usernamePasswordToken.setRememberMe(true);

        try {
            // 去验证当前登录的用户是否在shiro.ini配置文件当中(不抛出异常说明认证成功)
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            System.out.println("用户不存在，请重新输入！");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            System.out.println("密码错误，请重新输入！");
            e.printStackTrace();
        }
        // 判断是否认证成功(判断是否处于登录状态)
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认证成功，已登录成功 = " + authenticated);
        // 退出登录
        subject.logout();
        boolean authenticated1 = subject.isAuthenticated();
        System.out.println("用户是否登录 = " + authenticated1);
    }
}














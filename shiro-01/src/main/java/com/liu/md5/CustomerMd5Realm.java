package com.liu.md5;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomerMd5Realm extends AuthorizingRealm {

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("当前正在进行授权.....");
        // 一个用户可以有多个身份信息，但是只有一个主要的身份信息
        // 获取主要的身份信息（凭证）
        String principal = (String) principalCollection.getPrimaryPrincipal();
        // 获取授权对象信息（根据身份信息，用户名），获取当前登录用户的角色信息以及权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 将查询数据库中获取到的角色信息赋值给权限对象
        authorizationInfo.addRole("admin");
        authorizationInfo.addRole("sale");

        // 设置资源的权限
        authorizationInfo.addStringPermission("user:update:02");
        // 对01商品拥有所有的权限
        authorizationInfo.addStringPermission("product:*:01");

        // 返回权限对象
        return authorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户信息凭证
        String principal = (String) authenticationToken.getPrincipal();
        // 判断当前的信息凭证是否正确（当前的密码使用的是加密后的）
        if ("zhangsan".equals(principal)) {
            // 参数1：数据库用户名   参数2：数据库的加密的md5密码   参数3：realm的名字
            // 只是使用md5加密的方式
            //            SimpleAuthenticationInfo authenticationInfo =
            //                    new SimpleAuthenticationInfo(principal, "202cb962ac59075b964b07152d234b70", this.getName());

                        // 使用的是md5 + salt的算法(前端的就不需要在重新添加salt的方式)
            //            SimpleAuthenticationInfo authenticationInfo =
            //                    new SimpleAuthenticationInfo(principal,
            //                            "270d5d4db020e66d6a1118722a029cec",
            //                            ByteSource.Util.bytes("lms"),  // 注册时的随即盐salt
            //                            this.getName());  //

            // 使用的是md5 + salt + hash的算法(前端的就不需要在重新添加salt的方式,但是必须告知散列的次数)
            SimpleAuthenticationInfo authenticationInfo =
                    new SimpleAuthenticationInfo(principal,
                            "bad16f62a1256adf2989080d6be70cb7",
                            ByteSource.Util.bytes("lms"),  // 注册时的随即盐salt
                            this.getName());

            return authenticationInfo;
        }
        return null;
    }
}

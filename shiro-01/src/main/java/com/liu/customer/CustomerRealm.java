package com.liu.customer;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义Realm：目的是修改域直接从数据库中进行查询用户的信息
 * 将认证和授权的数据来源转为数据库的实现
 */
public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 通过token获取当前登录的用户凭证(用户名)
        String principal = (String) authenticationToken.getPrincipal();
        // System.out.println("principal = " + principal);
        if ("zhangsan".equals(principal)){
            /**
             * 参数1：返回数据库中正确的用户名
             * 参数2：返回数据库中正确的密码
             * 参数3：提供当前realm的名字，this.getName()
             */
            SimpleAuthenticationInfo authenticationInfo =
                    new SimpleAuthenticationInfo(principal, "123", this.getName());
            return authenticationInfo;
        }
        return null;
    }
}

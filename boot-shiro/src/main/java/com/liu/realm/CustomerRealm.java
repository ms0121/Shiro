package com.liu.realm;

import com.liu.entity.User;
import com.liu.service.UserService;
import com.liu.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

// 自定义的Realm实现
public class CustomerRealm extends AuthorizingRealm {

    // 关于为什么不能在这里使用@Autowired注解的方式获取容器中的bean对象？
    //   因为当前的这个类并没有交给spring工厂进行管理，所以在该类中也不能直接使用工厂注解的方式获取
    //   相应的bean对象，但是可以通过先获取工厂，然后在工厂中获取对应的bean对象
    //    @Autowired
    //    private UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户的主凭证信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        // 根据主凭证信息(用户信息)获取权限信息
        if ("zhangsan".equals(primaryPrincipal)) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            // 给用户设置角色
            authorizationInfo.addRole("admin");
            // 给用户添加资源权限
            authorizationInfo.addStringPermission("user:create:*");
            authorizationInfo.addStringPermission("user:update:*");
            return authorizationInfo;
        }
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取主体凭证信息
        String principal = (String) authenticationToken.getPrincipal();
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;

        // 获取spring容器中指定的bean对象，从而实现从数据库中查询用户信息
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findByUserName(principal);

        // 判断用户是否存在，存在则进行下一步的密码校验工作
        if (!ObjectUtils.isEmpty(user)) {
            // 认证密码
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, // 用户凭证(username)
                    user.getPassword(), // 用户密码
                    ByteSource.Util.bytes(user.getSalt()),  // salt
                    this.getName()); // 当前realm的名字
            return simpleAuthenticationInfo;
        }
        return null;
    }
}

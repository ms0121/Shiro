package com.liu.config;


import com.liu.cache.RedisCacheManager;
import com.liu.realm.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 用于整合shiro和springboot的框架
 */
@Configuration
public class ShiroConfig {

    // 创建ShiroFilter拦截器
    // 负责拦截所有的请求（需要使用安全管理器）
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 告知系统，哪些资源是需要进行认证和授权的
        // 配置系统的寿险资源
        // 配置系统的公共资源
        // key，表示访问的路径。value表示访问该路径需要的授权
        HashMap<String, String> map = new HashMap<>();
        // map.put("/index.jsp","authc"); // authc：表示请求这个资源需要认证和授权
        map.put("/user/login", "anon"); // anon: 表示不需要进行拦截（放行认证请求）
        map.put("/user/register", "anon"); // 用户注册请求 放行
        map.put("/register.jsp", "anon"); // 用户注册页面
        map.put("/order/*","anon");
        map.put("/**", "authc"); // 拦截所求的请求

        // 配置认证和授权的规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        // 默认的认证界面路径(login.jsp)
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        return shiroFilterFactoryBean;
    }

    // 创建安全管理器(需要设置realm)
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm到安全管理器
        securityManager.setRealm(realm);
        return securityManager;
    }

    // 创建自定义的realm域
    @Bean
    public Realm realm() {
        // 获取自定义的realm域
        CustomerRealm realm = new CustomerRealm();
        // 修改凭证校验校验器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置使用的加密算法以及散列的次数
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1024);
        // 将自定义的凭证校验器设置到自定义的域中
        realm.setCredentialsMatcher(matcher);

        // 开启本地缓存
        realm.setCacheManager(new EhCacheManager());
        // 使用自定义的redis缓存数据库
        // realm.setCacheManager(new RedisCacheManager());
        realm.setCachingEnabled(true); // 开启全局缓存数据库
        realm.setAuthenticationCachingEnabled(true); // 开启认证缓存
        realm.setAuthenticationCacheName("authenticationCache");
        realm.setAuthorizationCachingEnabled(true); // 开启授权缓存
        realm.setAuthorizationCacheName("authorizationCache");
        return realm;
    }
}












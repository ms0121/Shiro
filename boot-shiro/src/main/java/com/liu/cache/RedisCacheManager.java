package com.liu.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

// 自定义Shiro缓存管理器
public class RedisCacheManager implements CacheManager {
    // 参数s:代表的是认证或者是授权的统一名称
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        // System.out.println("cacheName = " + cacheName);
        return new RedisCache<K, V>(cacheName);
    }
}

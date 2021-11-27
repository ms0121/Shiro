package com.liu.cache;


import com.liu.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

//自定义redis缓存的实现
//因为当前的这个类并不是Spring工厂进行管理，所以无法直接使用注解的方式获取容器中的bean对象
//但是可以直接使用我们编写的工具类直接从容器中获取指定的额bean的对象
public class RedisCache<k, v> implements Cache<k, v> {

    private String cacheName;

    public RedisCache() {
    }

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public v get(k k) throws CacheException {
//        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
//        // 将k转为序列化的方式
//        redisTemplate.setStringSerializer(new StringRedisSerializer());
        // opsForHash表示当前获取的数据是一个map<string, map<>>,里面还有一个map结构
        return  (v) getRedisTemplate().opsForHash().get(this.cacheName, k.toString());
        // return (v) getRedisTemplate().opsForValue().get(k.toString());
    }

    @Override
    public v put(k k, v v) throws CacheException {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.opsForHash().put(this.cacheName, k.toString(), v);
        // redisTemplate.opsForValue().set(k.toString(), v);
        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<k> keys() {
        return null;
    }

    @Override
    public Collection<v> values() {
        return null;
    }

    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        // 将k转为序列化的方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 因为使用的是map<string, map<string, Object>>，所以还要设置里k的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}

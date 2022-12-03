package com.hikaru.serviceUtil;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings(value = {"unchecked", "rawtypes", "missing "})
public class RedisCache {
    @Autowired
    @Qualifier(value = "redisTemplate")
    RedisTemplate redisTemplate;

    /**
     * 缓存基本对象和实体类
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本对象和实体类并设置过期时间
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value, final Long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     *设置有效时间（默认为秒）
     * @param key
     * @param timeout
     * @return
     */
    public boolean expire(final String key, final Long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    public Boolean expire(final String key, final Long timeout, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 获取缓存的对象
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 删除单个对象
     * @param key
     * @return
     */
    public Boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     * @param collection
     * @return
     */
    public Long deleteCollection(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     * @param key
     * @param dataList
     * @param <T>
     * @return
     */
    public <T> Long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 缓存集合数据
     * @param key
     * @param dataSet
     * @param <T>
     * @return
     */
    public <T> BoundSetOperations setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps(key);
        for (T t : dataSet) {
            boundSetOperations.add(t);
        }
        return boundSetOperations;
    }

    /**
     * 获取缓存的集合数据
     * @param key
     * @param <T>
     * @return
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存哈希数据
     * @param key
     * @param hKey
     * @param data
     * @param <T>
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T data) {
        redisTemplate.opsForHash().put(key, hKey, data);
    }

    /**
     * 获取哈希数据
     * @param key
     * @param hKey
     * @param <T>
     * @return
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, hKey);
    }

    /**
     * 删除哈希中的数据
     * @param key
     * @param hKey
     */
    public void deleteCacheMapValue(final String key, final String hKey) {
        redisTemplate.opsForHash().delete(key, hKey);
    }

    /**
     * 获取哈希中的多个值
     * @param key
     * @param hKeys
     * @param <T>
     * @return
     */
    public <T> List<T> getCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获取缓存的基本对象列表
     * @param pattern 键值的字符串前缀
     * @return
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}

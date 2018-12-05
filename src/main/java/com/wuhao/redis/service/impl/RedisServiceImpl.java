package com.wuhao.redis.service.impl;

import com.wuhao.redis.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 设置redis和cache
     * @param redisName
     * @param redisCon
     * @return
     */
    @Override
    @Cacheable(key = "#redisName",cacheNames = "redis")
    public String setRedis(String redisName, String redisCon) {
       if (StringUtils.isBlank(redisCon)){
           return null;
       }
       return redisCon;
    }

    /**
     * 根据redis的名称获取redis的值
     * @param redisName
     * @return
     */
    @Override
    public String getRedis(String redisName) {
        if (StringUtils.isBlank(redisName)){
            return null;
        }
        String redisCon = redisTemplate.opsForValue().get("redis::" + redisName);
        if (StringUtils.isBlank(redisCon)){
            return null;
        }
        return redisCon;
    }
}

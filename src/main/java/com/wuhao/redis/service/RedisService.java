package com.wuhao.redis.service;

public interface RedisService {
    String setRedis(String redisName,String redisCon);
    String getRedis(String redisName);
}

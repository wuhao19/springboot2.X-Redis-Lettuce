package com.wuhao.redis.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.wuhao.redis.entity.User;
import com.wuhao.redis.mapper.UserMapper;
import com.wuhao.redis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuhao
 * @since 2018-11-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private Gson json = new Gson();

    @Override
    public List<User> findUserByName(String userName) {
        List<User> name = userMapper.selectList(new QueryWrapper<User>().eq("name", userName));
        String userJson = json.toJson(name);
        redisTemplate.opsForValue().set("userList",userJson);
        return name;
    }

    @Override
    public User findUserById(Integer userId) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        String userJson = json.toJson(user);
        redisTemplate.opsForValue().set("user",userJson);
        return user;
    }

    @Override
    public boolean saveBatch(Collection<User> entityList) {
        return false;
    }
}

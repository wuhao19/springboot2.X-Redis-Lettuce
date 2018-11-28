package com.wuhao.redis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuhao.redis.entity.User;
import com.wuhao.redis.mapper.UserMapper;
import com.wuhao.redis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    @Override
    @Cacheable(key = "#userId",cacheNames = "user")
    public User findOneUserById(int userId) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        int result = userMapper.update(user, new QueryWrapper<User>().eq("id", user.getId()));
        if (result==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        int result = userMapper.insert(user);
        if (result==1){
            return true;
        }else{
            return false;
        }
    }
}

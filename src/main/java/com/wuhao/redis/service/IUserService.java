package com.wuhao.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuhao.redis.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuhao
 * @since 2018-11-28
 */
public interface IUserService extends IService<User> {
    List<User> findUserByName(String userName);
    User findUserById(Integer userId);
}

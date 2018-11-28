package com.wuhao.redis.controller;


import com.wuhao.redis.entity.User;
import com.wuhao.redis.model.ApiModel;
import com.wuhao.redis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuhao
 * @since 2018-11-28
 */
@Controller
@RequestMapping("/redis/user")
public class UserController{
    @Autowired
    private IUserService userService;

    @ResponseBody
    @GetMapping("/findUserByName")
    public ApiModel findUserByName(@RequestParam(value = "userName")String userName){
        List<User> userByName = userService.findUserByName(userName);
        ApiModel apiModel = new ApiModel();
        Map<String,Object> map = new HashMap<>();
        map.put("userList",userByName);
        apiModel.setMap(map);
        return apiModel;
    }

    @ResponseBody
    @GetMapping("/findUserById")
    public ApiModel findUserById(@RequestParam(value = "userId")int userId){
        User user = userService.findUserById(userId);
        ApiModel apiModel = new ApiModel();
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        apiModel.setMap(map);
        return apiModel;
    }

}

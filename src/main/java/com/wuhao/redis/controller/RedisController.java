package com.wuhao.redis.controller;

import com.wuhao.redis.model.ApiModel;
import com.wuhao.redis.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @PostMapping("/setRedis")
    public ApiModel setRedis(@RequestParam(value = "redisName")String redisName,
                             @RequestParam(value = "redisCon")String redisCon){
    if (StringUtils.isBlank(redisCon)||StringUtils.isBlank(redisName)){
        return new ApiModel("设置的内容不能为空");
    }
    if (redisService.setRedis(redisName, redisCon)==null){
        return new ApiModel("设置redis失败！");
    }
    return new ApiModel();
    }

    @PostMapping("/getRedis")
    public ApiModel getRedis(@RequestParam(value = "redisName")String redisName){
        if (StringUtils.isBlank(redisName)){
            return new ApiModel("查找的redis名称不能为空");
        }
        String redisCon = redisService.getRedis(redisName);
        if (redisCon==null){
            return new ApiModel("你查找的redis不存在！");
        }
        ApiModel apiModel = new ApiModel();
        Map<String,Object> map = new HashMap<>();
        map.put("redis","redisName->"+redisName+";redisCon->"+redisCon);
        apiModel.setMap(map);
        return apiModel;
    }
}

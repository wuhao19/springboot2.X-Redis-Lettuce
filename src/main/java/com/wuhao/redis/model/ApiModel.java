package com.wuhao.redis.model;

import lombok.Data;

import java.util.Map;

@Data
public class ApiModel {
    private Integer code;
    private String message;
    private Map<String,Object> map;

    public ApiModel(String message){
        this.message = message;
        this.code=1;
    }
    public ApiModel(){
        this.message = "";
        this.code = 0;
    }

}

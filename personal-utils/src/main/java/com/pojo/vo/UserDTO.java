package com.pojo.vo;

import com.alibaba.fastjson.JSON;
import lombok.ToString;

/**
 * Description : [类描述]
 * Created on : 2018年03月02日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
public class UserDTO extends PersonDTO {

    private static final long serialVersionUID = 7096281061139815877L;

    private Integer age ;

    public UserDTO() {
    }

    @Override
    public String toString() {
        return super.toString() + JSON.toJSONString(this);
    }

    public static void main(String[] args) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(5);
        userDTO.setName("ball");
        System.out.println(userDTO.toString());
    }
}

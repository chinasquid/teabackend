package com.teabackend.teabackend.userbase.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 前端接收注册对象数据
 * @author shuyang
 * @Description:
 * @create 2020-05-11 14:29
 */
@Data
public class UserRegisterVO {
    /**
     * 用户名
     */
    private String user_name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
}

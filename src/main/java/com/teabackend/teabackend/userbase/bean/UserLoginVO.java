package com.teabackend.teabackend.userbase.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-11 17:25
 */
@Data
public class UserLoginVO {
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
}

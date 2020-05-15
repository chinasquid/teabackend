package com.teabackend.teabackend.userbase.bean;

import lombok.Data;

/**
 * 存储入数据库的对象
 * @author shuyang
 * @Description:
 * @create 2020-05-11 15:45
 */
@Data
public class UserRegisterDO {
    /**
     * 用户id
     */
    private String user_id;
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
    /**
     * 用户类型
     */
    private Integer user_type;
}

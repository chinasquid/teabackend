package com.teabackend.teabackend.userbase.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-11 18:38
 */
@Data
public class UserBodyDO {
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
     * 用户类型
     */
    private Integer user_type;
    /**
     * 用户头像
     */
    private String head_img;
}

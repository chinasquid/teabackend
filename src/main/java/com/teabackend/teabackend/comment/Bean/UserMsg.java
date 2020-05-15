package com.teabackend.teabackend.comment.Bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-11 17:19
 */
@Data
public class UserMsg {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户类型
     */
    private Integer userType;
}

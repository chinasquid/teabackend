package com.teabackend.teabackend.business.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 14:00
 */
@Data
public class BusinessAllInformationDTO {
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
    /**
     * 用户头像
     */
    private String head_img;
    /**
     * 发货地址
     */
    private String address;
    /**
     * 店铺类型
     */
    private String store_type;
    /**
     * 主营业茶叶类型
     */
    private String main_tea_type;
    /**
     * 主营业茶叶品种
     */
    private String main_tea_variety;
    /**
     * 主营业茶叶品牌
     */
    private String main_tea_brand;
    /**
     * 背景图地址
     */
    private String back_img_virtual;
}

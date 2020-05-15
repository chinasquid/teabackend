package com.teabackend.teabackend.search.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 17:11
 */
@Data
public class StoreItemDTO {
    /**
     * 头像地址
     */
    private String head_img;
    /**
     * 店铺名字
     */
    private String user_name;
    /**
     * 主营茶叶类型
     */
    private String main_tea_type;
    /**
     * 主营茶叶品种
     */
    private String main_tea_variety;
    /**
     * 主营茶叶品牌
     */
    private String main_tea_brand;
    /**
     * 店铺类型
     */
    private String store_type;
    /**
     * 发货地址
     */
    private String address;
    /**
     * 背景图虚拟访问地址
     */
    private String back_img_virtual;
}

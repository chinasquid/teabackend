package com.teabackend.teabackend.business.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 13:57
 */
@Data
public class StoreInformationDO {
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

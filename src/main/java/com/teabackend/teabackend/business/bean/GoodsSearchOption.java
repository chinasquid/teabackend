package com.teabackend.teabackend.business.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-13 20:40
 */
@Data
public class GoodsSearchOption {
    /**
     * 搜索类型
     */
    private String search_type;
    /**
     * 茶叶类型
     */
    private String tea_type;
    /**
     * 茶叶品种
     */
    private String tea_variety;
    /**
     * 茶叶品牌
     */
    private String tea_brand;
    /**
     * 茶叶用途
     */
    private String purpose;
    /**
     * 茶叶季节
     */
    private String tea_season;
    /**
     * 价格范围
     */
    private String price;
    /**
     * 页数
     */
    private String page;
    /**
     * 大小
     */
    private String size;
}

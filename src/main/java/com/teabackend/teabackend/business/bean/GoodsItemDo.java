package com.teabackend.teabackend.business.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-13 11:30
 */
@Data
public class GoodsItemDo {
    /**
     * 商品标题
     */
    private String goods_tittle;
    /**
     * 商品描述
     */
    private String goods_describe;
    /**
     * 图片虚拟访问地址
     */
    private String file_virtual_path;
    /**
     * 商品用途
     */
    private String purpose;
    /**
     * 商品类型
     */
    private String tea_type;
    /**
     * 茶叶品种
     */
    private String tea_variety;
    /**
     * 商品品牌
     */
    private String tea_brand;
    /**
     * 茶叶季节
     */
    private String tea_season;
    /**
     * 商品单价
     */
    private Double price;
    /**
     * 邮费
     */
    private Double post;
    /**
     * 前端是否展示标识
     */
    private String show = "0";
}

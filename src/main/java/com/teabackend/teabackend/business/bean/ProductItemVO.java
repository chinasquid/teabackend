package com.teabackend.teabackend.business.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-12 21:39
 */
@Data
public class ProductItemVO {
    /**
     * 用户id
     */
    private String user_id;
    /**
     * 图片id
     */
    private String img_id;
    /**
     * 商品标题
     */
    private String goods_tittle;
    /**
     * 商品描述
     */
    private String goods_describe;
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
     * 是否有邮费
     */
    private Double post;
}

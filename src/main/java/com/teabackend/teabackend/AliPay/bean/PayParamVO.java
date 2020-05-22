package com.teabackend.teabackend.AliPay.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-15 21:02
 */
@Data
public class PayParamVO {
    /**
     * 商品id
     */
    private String goods_id;
    /**
     * 商品标题
     */
    private String goods_tittle;
    /**
     * 商品描述
     */
    private String goods_describe;
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
     * 茶叶季节
     */
    private String tea_season;
    /**
     * 单价
     */
    private Double price;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 邮费
     */
    private String post;
    /**
     * 总价
     */
    private Double all_price;
    /**
     * 商店名称
     */
    private String store_name;
    /**
     * 商店id
     */
    private String store_id;
    /**
     * 用户id
     */
    private String user_id;
    /**
     * 交易类型
     */
    private Integer payType;
    /**
     * 收货地址
     */
    private String receiving_address;
}

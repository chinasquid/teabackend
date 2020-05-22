package com.teabackend.teabackend.orderbpm.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-17 14:03
 */
@Data
public class WaitPayDO {
    /**
     * 订单编号
     */
    private String out_trade_no;
    /**
     * 订单标题
     */
    private String subject;

    /**
     * 订单总金额
     */
    private String total_amount;

    /**
     * 卖家支付宝账号ID
     */

    private String seller_id;

    /**
     * 订单描述
     */
    private String body;

    /**
     * (必填) 商户门店编号
     */
    private String store_id;
    /**
     * 二维码地址
     */

    private String qr_path;
    /**
     * 商品id
     */
    private String goods_id;
    /**
     * 商品标题
     */
    private String goods_tittle;
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
     * 商店名称
     */
    private String store_name;
    /**
     * 交易类型
     */
    private Integer payType;
    /**
     * 收货地址
     */
    private String receiving_address;
    /**
     * 创建的天数
     */
    private String create_day;
    /**
     * 创建的时间
     */
    private String create_time;
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


}

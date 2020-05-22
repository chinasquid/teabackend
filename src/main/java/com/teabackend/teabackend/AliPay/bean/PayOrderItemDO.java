package com.teabackend.teabackend.AliPay.bean;

import com.alipay.demo.trade.model.ExtendParams;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-15 20:54
 */
@Data
public class PayOrderItemDO implements Serializable {
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

    private String total_amount ;

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
     * 购买的用户id
     */

    private String user_id;
    /**
     * 订单状态
     */

    private String order_status;
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
}

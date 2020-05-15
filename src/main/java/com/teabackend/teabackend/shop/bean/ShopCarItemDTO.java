package com.teabackend.teabackend.shop.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-15 14:18
 */
@Data
public class ShopCarItemDTO {
    /**
     * 商品id
     */
    private Integer goods_id;
    /**
     * 商品标题
     */
    private String goods_tittle;
    /**
     * 商品描述
     */
    private String goods_describe;
    /**
     * 图片访问路径
     */
    private String file_virtual_path;
    /**
     *单价
     */
    private BigDecimal price;
    /**
     *购买数量
     */
    private Integer number;
    /**
     *总价
     */
    private BigDecimal all_price;
    /**
     *用户id
     */
    private String user_id;
}

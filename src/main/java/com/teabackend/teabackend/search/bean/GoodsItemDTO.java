package com.teabackend.teabackend.search.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 21:57
 */
@Data
public class GoodsItemDTO {
    /**
     * 商品id
     */
    private Integer id;
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
     * 商品评分
     */
    private Double score;
    /**
     * 商家名称
     */
    private String user_name;
}

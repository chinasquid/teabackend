package com.teabackend.teabackend.search.bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 17:05
 */
@Data
public class SearchStoreTypeVO {
    /**
     * 模糊搜索名称
     */
    private String search_name;
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
     * 页数
     */
    private Integer page;
    /**
     * 大小
     */
    private Integer size;
}

package com.teabackend.teabackend.shop.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-16 21:37
 */
@Data
public class AddressItemDO implements Serializable {
    /**
     * 用户id
     */
    private String user_id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 地址
     */
    private String address;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 收货地址id
     */
    private Integer address_id;
}

package com.teabackend.teabackend.shop.dao;

import com.teabackend.teabackend.shop.bean.ShopCarItemDTO;
import com.teabackend.teabackend.shop.bean.ShopOrderItemVO;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-15 10:33
 */
@Mapper
public interface ShopBaseDao {
    /**
     * 此人是否含有此商品
     *
     * @param user_id
     * @param goods_id
     * @return
     */
    @Select("select number from shop_car where user_id = #{user_id} and goods_id = #{goods_id}")
    Integer selectShopOrderNum(@Param("user_id") String user_id, @Param("goods_id") Integer goods_id);

    /**
     * 更新数据
     *
     * @param shopOrderItemVO 信息
     */
    @Update("update shop_car set number=#{number},all_price=#{all_price} where goods_id = #{goods_id} and user_id=#{user_id}")
    void updateNewShopItem(ShopOrderItemVO shopOrderItemVO);

    /**
     * 插入数据
     *
     * @param shopOrderItemVO 信息
     */
    @Insert("insert into shop_car(goods_id,file_virtual_path,price,number,all_price,user_id) values (#{goods_id},#{file_virtual_path},#{price},#{number},#{all_price},#{user_id})")
    void insertNewShopItem(ShopOrderItemVO shopOrderItemVO);

    /**
     * 获取单人购物车信息
     *
     * @param user_id 用户id
     * @return 购物车信息
     */
    @Select("select goods_id ,a.file_virtual_path, a.price,number,all_price,a.user_id,goods_tittle,goods_describe " +
            "from shop_car a INNER JOIN temp_goods b on a.goods_id = b.id " +
            "where a.user_id = #{user_id}")
    ArrayList<ShopCarItemDTO> selectShoppingCarList(@Param("user_id") String user_id);

    /**
     * 删除购物车内商品
     *
     * @param goods_id 商品id
     * @param user_id  用户id
     */
    @Delete("delete from shop_car where goods_id = #{goods_id} and user_id = #{user_id}")
    void deleteShopCarItem(@Param("goods_id") Integer goods_id, @Param("user_id") String user_id);

    /**
     * 更新数量
     * @param shopCarItemDTO
     */
    @Update("update shop_car set number = #{number} ,all_price = #{all_price} where goods_id = #{goods_id} and user_id = #{user_id}")
    void updateShopItemNumber(ShopCarItemDTO shopCarItemDTO);
}

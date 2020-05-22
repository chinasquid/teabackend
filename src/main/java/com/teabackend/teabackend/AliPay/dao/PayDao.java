package com.teabackend.teabackend.AliPay.dao;

import com.teabackend.teabackend.AliPay.bean.PayOrderItemDO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-15 20:53
 */
@Mapper
public interface PayDao {
    /**
     * 创建新订单
     * @param payOrderItemDO
     */
    @Insert("insert into pay_order(out_trade_no,subject,total_amount,seller_id,body,store_id,user_id,order_status,qr_path,goods_id,goods_tittle,price,number,post,payType,store_name,receiving_address,create_day,create_time) " +
            "values (#{out_trade_no},#{subject},#{total_amount},#{seller_id},#{body},#{store_id},#{user_id},#{order_status},#{qr_path},#{goods_id},#{goods_tittle},#{price},#{number},#{post},#{payType},#{store_name},#{receiving_address},#{create_day},#{create_time})")
    void insertNewOrder(PayOrderItemDO payOrderItemDO);

    /**
     * 轮询查询订单状态
     * @param outTradeNo
     * @param status
     */
    @Update("update pay_order set order_status = #{status} where out_trade_no = #{outTradeNo}")
    void updateOrderStatus(@Param("outTradeNo") String outTradeNo,@Param("status") Integer status);

    /**
     * 更新流程状态
     * @param outTradeNo
     * @param bpmStatus
     */
    @Update("update pay_order set bpm_status = #{bpmStatus} where out_trade_no = #{outTradeNo}")
    void setBpmStatus(@Param("outTradeNo") String outTradeNo, @Param("bpmStatus") Integer bpmStatus);

    /**
     * 查询待支付的订单
     * @param userId
     * @return
     */
    @Select("select out_trade_no from pay_order where user_id = #{userId} and order_status = 2")
    ArrayList<String> selectWaitPayOrderList(String userId);
}

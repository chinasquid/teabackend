package com.teabackend.teabackend.orderbpm.dao;

import com.teabackend.teabackend.orderbpm.bean.FinishOrderDO;
import com.teabackend.teabackend.orderbpm.bean.WaitDeliverDO;
import com.teabackend.teabackend.orderbpm.bean.WaitPayDO;
import com.teabackend.teabackend.orderbpm.bean.WaitReceiptDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-17 13:22
 */
@Mapper
public interface UserOrderBpmDao {
    /**
     * 获取待支付信息
     *
     * @param user_id
     * @return
     */
    @Select("select out_trade_no,body,total_amount,a.goods_tittle,store_name,a.price,number,a.post,total_amount,tea_type,tea_variety,tea_brand,purpose,tea_season,create_day,create_time,qr_path,store_id " +
            "from pay_order a INNER JOIN temp_goods b on a.goods_id = b.id " +
            "where a.user_id = #{user_id} and order_status = 2")
    ArrayList<WaitPayDO> getWaitPayOrder(@Param("user_id") String user_id);

    /**
     * 获取待发货信息
     *
     * @param user_id 用户id
     * @return
     */
    @Select("select out_trade_no,body,total_amount,a.goods_tittle,store_name,a.price,number,a.post,total_amount,tea_type,tea_variety,tea_brand,purpose,tea_season,create_day,create_time,store_id " +
            "from pay_order a INNER JOIN temp_goods b on a.goods_id = b.id " +
            "where a.user_id = #{user_id} and order_status = 1 and bpm_status = 2")
    ArrayList<WaitDeliverDO> getWaitDeliverOrder(@Param("user_id") String user_id);

    /**
     * 获取待收货信息
     *
     * @param user_id
     * @return
     */
    @Select("select out_trade_no,body,total_amount,a.goods_tittle,store_name,a.price,number,a.post,total_amount,tea_type,tea_variety,tea_brand,purpose,tea_season,create_day,create_time,store_id,deliver_day,deliver_time " +
            "from pay_order a INNER JOIN temp_goods b on a.goods_id = b.id " +
            "where a.user_id = #{user_id} and order_status = 1 and bpm_status = 3")
    ArrayList<WaitReceiptDO> getWaitReceiptOrder(@Param("user_id") String user_id);

    /**
     * 确认收货
     *
     * @param out_trade_no 订单编号
     * @param userId 用户id
     * @param day 日期
     * @param time 时间
     */
    @Update("update pay_order set bpm_status = 4 ,receipt_day = #{receipt_day} ,receipt_time = #{receipt_time} where out_trade_no = #{out_trade_no} and user_id = #{user_id}")
    void confirmReceipt(String out_trade_no, String userId, @Param("receipt_day") String day, @Param("receipt_time") String time);

    /**
     * 获取已完成信息
     *
     * @param user_id
     * @return
     */
    @Select("select out_trade_no,body,total_amount,a.goods_tittle,store_name,a.price,number,a.post,total_amount,tea_type,tea_variety,tea_brand,purpose,tea_season,create_day,create_time,store_id,deliver_day,deliver_time,receipt_day,receipt_time " +
            "from pay_order a INNER JOIN temp_goods b on a.goods_id = b.id " +
            "where a.user_id = #{user_id} and order_status = 1 and bpm_status = 4")
    ArrayList<FinishOrderDO> getFinallyOrder(@Param("user_id") String user_id);


}

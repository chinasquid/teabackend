package com.teabackend.teabackend.userbase.dao;

import com.teabackend.teabackend.search.bean.GoodsItemDTO;
import com.teabackend.teabackend.userbase.bean.UserBodyDO;
import com.teabackend.teabackend.userbase.bean.UserLoginVO;
import com.teabackend.teabackend.userbase.bean.UserRegisterDO;
import org.apache.ibatis.annotations.*;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-11 12:53
 */
@Mapper
public interface BaseDao {

    /**
     * 查看是否有此用户
     *
     * @param phone
     * @return
     */
    @Select("select count(phone) from account where phone = #{phone}")
    Integer selectAccountNum(@Param("phone") String phone);

    /**
     * 注册时插入账号表信息
     *
     * @param userRegisterDO
     */
    @Insert("insert into account (phone,password,user_type) values (#{phone},#{password},#{user_type})")
    void insertRegisterAccount(UserRegisterDO userRegisterDO);

    /**
     * 注册时插入用户信息表信息
     *
     * @param userRegisterDO
     */
    @Insert("insert into user_body (phone,user_id,user_name) values (#{phone},#{user_id},#{user_name})")
    void insertRegisterInformation(UserRegisterDO userRegisterDO);

    /**
     * 查看生成的userId是否重复
     *
     * @param code 生成的6位数id
     * @return
     */
    @Select("select count(user_id) from user_body where user_id = #{code}")
    Integer selectIdNum(@Param("code") String code);

    /**
     * 查询用户类型
     *
     * @param phone 手机号码
     * @return
     */
    @Select("select user_type from account where phone = #{phone}")
    Integer selectUserType(@Param("phone") String phone);

    /**
     * 查询用户基础信息
     *
     * @param phone 手机号码
     * @return
     */
    @Select("select * ,(select user_type from account where phone = #{phone}) user_type from user_body where phone = #{phone}")
    UserBodyDO selectUserBody(@Param("phone") String phone);

    /**
     * 查询是否有对应账号数据
     *
     * @param phone
     * @param password
     * @return
     */
    @Select("select count(*) from account where phone = #{phone} and password = #{password}")
    Integer selectHaveAccountNum(@Param("phone") String phone, @Param("password") String password);

    /**
     * 升级为商家
     * @param phone 手机号码
     */
    @Update("update account set user_type = 2 where phone = #{phone}")
    void UpdateUserType(@Param("phone") String phone);

    /**
     * 插入商家信息到店铺表
     * @param user_id 用户id
     */
    @Insert("insert into store_body (user_id) values(#{user_id})")
    void insertStoreBody(@Param("user_id") String user_id);
}

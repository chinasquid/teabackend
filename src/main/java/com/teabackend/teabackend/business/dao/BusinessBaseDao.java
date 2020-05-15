package com.teabackend.teabackend.business.dao;

import com.teabackend.teabackend.business.bean.BusinessAllInformationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 13:56
 */
@Mapper
public interface BusinessBaseDao {
    /**
     * 获取店铺信息
     * @return 店铺信息
     * @param user_id 用户id
     * @param phone 手机号码
     */
    @Select("select store_type,main_tea_type,main_tea_variety,main_tea_brand,address ,(select password from account where phone = #{phone}) password from store_body where user_id = #{user_id}")
    BusinessAllInformationDTO selectStoreInformation(@Param("user_id") String user_id,@Param("phone") String phone);

    /**
     * 获取密码
     * @param phone 电话号码
     * @return 密码
     */
    @Select("select password from account where phone = #{phone}")
    String getPassword(@Param("phone") String phone);
    /**
     * 更新店铺信息
     * @param allInformationDTO 店铺信息
     */
    @Update("update store_body set store_type = #{store_type},main_tea_type = #{main_tea_type},main_tea_variety = #{main_tea_variety},main_tea_brand = #{main_tea_brand},address = #{address} where user_id = #{user_id}")
    void updateInformation(BusinessAllInformationDTO allInformationDTO);

    /**
     * 保存店铺背景图片路径
     * @param fileRealPath 文件真实位置
     * @param fileVirtualPath 文件虚拟访问位置
     * @param user_id 用户ID
     */
    @Update("update store_body set back_img_real = #{fileRealPath}, back_img_virtual = #{fileVirtualPath},where user_id = #{user_id}")
    void saveBackImgPath(@Param("fileRealPath") String fileRealPath,@Param("fileVirtualPath") String fileVirtualPath,@Param("user_id") String user_id);
}

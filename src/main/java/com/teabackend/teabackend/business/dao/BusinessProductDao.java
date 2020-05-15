package com.teabackend.teabackend.business.dao;

import com.teabackend.teabackend.business.bean.GoodsItemDo;
import com.teabackend.teabackend.business.bean.GoodsListItemDO;
import com.teabackend.teabackend.business.bean.GoodsSearchOption;
import com.teabackend.teabackend.business.bean.ProductItemVO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-12 21:01
 */
@Mapper
public interface BusinessProductDao {

    /**
     * 暂存商品图片地址
     *
     * @param fileRealPath 文件真实路径
     * @param fileVirtualPath 文件虚拟访问路径
     * @param userId   用户id
     * @param img_id   图片id
     */
    @Select("insert into temp_goods (file_real_path,file_virtual_path,user_id,img_id) values (#{fileRealPath},#{fileVirtualPath},#{user_id},#{img_id})")
    void insertGoodTempPath(@Param("fileRealPath") String fileRealPath,@Param("fileVirtualPath")String fileVirtualPath, @Param("user_id") String userId, @Param("img_id") String img_id);

    /**
     * 删除是否有对应的暂存图片
     * @param userId 用户id
     * @param imgId  图片id
     * @return 图片数量
     */
    @Delete("delete from temp_goods where user_id = #{user_id} and img_id = #{img_id}")
    void deleteTempImg(@Param("user_id") String userId, @Param("img_id") String imgId);

    /**
     * 保存商品信息
     *
     * @param productItemVO 商品具体信息
     */
    @Update("update temp_goods set goods_tittle = #{goods_tittle} ,goods_describe =#{goods_describe},purpose=#{purpose},tea_type=#{tea_type},price=#{price},post=#{post},tea_brand=#{tea_brand},tea_variety = #{tea_variety},tea_season = #{tea_season} where user_id =#{user_id} and img_id = #{img_id}")
    void updateGoodsDetails(ProductItemVO productItemVO);

    /**
     * 获取商品全部信息
     * @param user_id 用户id
     * @return 商品信息列表
     */
    @Select("select goods_tittle,goods_describe,tea_type,price,post,tea_brand,purpose,file_virtual_path,tea_season,tea_variety\n" +
            "from temp_goods a\n" +
            "where user_id = #{user_id} \n" +
            "and goods_tittle != ''")
    ArrayList<GoodsItemDo> selectGoodsList(@Param("user_id") String user_id);

    /**
     * 查询此产品数量
     * @param user_id 用户id
     * @param img_id 图片id
     * @return 数量
     */
    @Select("select count(*) from temp_goods where user_id = #{user_id} and img_id = #{img_id}")
    Integer selectGoodsNum(@Param("user_id") String user_id,@Param("img_id") String img_id);

    /**
     * 搜索商品
     * @param searchOption
     * @return
     */
    @Select("select goods_tittle,goods_describe,file_virtual_path,purpose,tea_type,tea_variety,tea_brand,tea_season,price,post,score,user_name " +
            "from temp_goods a " +
            "INNER JOIN user_body b on a.user_id = b.user_id " +
            "where goods_tittle!='' ")
    ArrayList<GoodsListItemDO> getAllGoodsList(GoodsSearchOption searchOption);
}

package com.teabackend.teabackend.search.dao;

import com.teabackend.teabackend.search.bean.GoodsItemDTO;
import com.teabackend.teabackend.search.bean.SearchGoodsTypeVO;
import com.teabackend.teabackend.search.bean.SearchStoreTypeVO;
import com.teabackend.teabackend.search.bean.StoreItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 17:01
 */
@Mapper
public interface SearchDao {
    /**
     * 商家搜索
     * @param searchGoodsTypeVO 搜索选项
     * @return 商家列表
     */
    @Select("<script>select store_type,main_tea_type,main_tea_variety,main_tea_brand,address ,main_tea_variety,back_img_virtual,b.head_img,b.user_name\n" +
            "from store_body a " +
            "INNER JOIN user_body b on a.user_id = b.user_id  " +
            "where 1=1 " +
            "<if test=\"search_name!= null and search_name!=''\">and b.user_name like concat('%',#{search_name},'%')</if > " +
            "<if test=\"main_tea_type!= null and main_tea_type!=''\">and main_tea_type = #{main_tea_type}</if >  " +
            "<if test=\"main_tea_variety !=null and main_tea_variety !=''\">and main_tea_variety=#{main_tea_variety}</if > " +
            "<if test=\"main_tea_brand !=null and main_tea_brand !=''\">and main_tea_brand=#{main_tea_brand}</if > " +
            "<if test=\"store_type !=null and store_type !=''\">and store_type=#{store_type}</if ></script>")
    ArrayList<StoreItemDTO> selectStoreList(SearchStoreTypeVO searchGoodsTypeVO);

    /**
     * 商品模糊搜索
     * @param searchGoodsTypeVO 筛选选项
     * @return 商品列表
     */
    @Select("<script>select a.id id ,goods_tittle,goods_describe,file_virtual_path,purpose,tea_type,tea_variety,tea_brand,tea_season,price,post,score,user_name\n" +
            "from temp_goods a \n" +
            "INNER JOIN user_body b on a.user_id = b.user_id \n" +
            "where goods_tittle!='' \n" +
            "<if test=\"search_name!= null and search_name!=''\">and goods_tittle like concat('%',#{search_name},'%')</if >\n" +
            "<if test=\"tea_type!= null and tea_type!=''\">and tea_type = #{tea_type}</if >\n" +
            "<if test=\"tea_variety !=null and tea_variety !=''\">and tea_variety=#{tea_variety}</if >\n" +
            "<if test=\"tea_brand !=null and tea_brand !=''\">and tea_brand=#{tea_brand}</if >\n" +
            "<if test=\"purpose !=null and purpose !=''\">and purpose=#{purpose}</if >\n" +
            "<if test=\"tea_season !=null and tea_season !=''\">and tea_season=#{tea_season}</if></script>")
    ArrayList<GoodsItemDTO> selectGoodsLIst(SearchGoodsTypeVO searchGoodsTypeVO);

    /**
     * 获取单个商品详细信息
     * @param goods_id 商品id
     * @return 商品详细信息
     */
    @Select("select goods_tittle,goods_describe,file_virtual_path,purpose,tea_type,tea_variety,tea_brand,tea_season,price,post,score,user_name,score\n" +
            "from temp_goods a " +
            "INNER JOIN user_body b on a.user_id = b.user_id " +
            "where goods_tittle!='' " +
            "and a.id = #{goods_id}")
    GoodsItemDTO getGoodsItem(@Param("goods_id") String goods_id);
}

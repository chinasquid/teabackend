package com.teabackend.teabackend.search.service;

import com.github.pagehelper.PageHelper;
import com.teabackend.teabackend.search.bean.GoodsItemDTO;
import com.teabackend.teabackend.search.bean.SearchGoodsTypeVO;
import com.teabackend.teabackend.search.bean.SearchStoreTypeVO;
import com.teabackend.teabackend.search.bean.StoreItemDTO;
import com.teabackend.teabackend.search.dao.SearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 17:01
 */
@Service
public class SearchService {
    @Autowired
    private SearchDao searchDao;

    /**
     * 商家搜索
     * @param searchStoreTypeVO 搜索选项
     * @return 商家列表
     */
    public ArrayList<StoreItemDTO> searchStoreList(SearchStoreTypeVO searchStoreTypeVO)throws Exception {
        ArrayList<StoreItemDTO> dtoArrayList = new ArrayList<>();
        PageHelper.startPage(searchStoreTypeVO.getPage(),searchStoreTypeVO.getSize());
        dtoArrayList = searchDao.selectStoreList(searchStoreTypeVO);
        return dtoArrayList;
    }

    /**
     * 商品模糊搜索
     * @param searchGoodsTypeVO 筛选选项
     * @return 商品列表
     */
    public ArrayList<GoodsItemDTO> searchGoodsLIst(SearchGoodsTypeVO searchGoodsTypeVO)throws Exception {
        PageHelper.startPage(searchGoodsTypeVO.getPage(),searchGoodsTypeVO.getSize());
        return searchDao.selectGoodsLIst(searchGoodsTypeVO);
    }

    /**
     * 获取单个商品详细信息
     * @param goods_id 商品id
     * @return 商品详细信息
     */
    public GoodsItemDTO getGoodsItem(String goods_id) throws Exception {
        return searchDao.getGoodsItem(goods_id);
    }
}

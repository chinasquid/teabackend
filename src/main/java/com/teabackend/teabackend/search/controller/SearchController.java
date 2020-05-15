package com.teabackend.teabackend.search.controller;

import com.teabackend.teabackend.comment.Bean.Result;
import com.teabackend.teabackend.search.bean.GoodsItemDTO;
import com.teabackend.teabackend.search.bean.SearchGoodsTypeVO;
import com.teabackend.teabackend.search.bean.SearchStoreTypeVO;
import com.teabackend.teabackend.search.bean.StoreItemDTO;
import com.teabackend.teabackend.search.service.SearchService;
import com.teabackend.teabackend.userbase.service.BaseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 17:00
 */
@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping("/searchGoods")
    public Result searchGoods(@RequestBody SearchGoodsTypeVO searchGoodsTypeVO){
        Result result = new Result();
        ArrayList<GoodsItemDTO> goodsItemDTOS = new ArrayList<>();
        try {
            goodsItemDTOS = searchService.searchGoodsLIst(searchGoodsTypeVO);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("搜索失败，请稍后重试");
            return result;
        }
        HashMap<Object, Object> hashMap = new HashMap<>();
        result.success("搜索成功！",goodsItemDTOS);
        return result;
    }

    @PostMapping("/searchStore")
    public Result searchStore(@RequestBody SearchStoreTypeVO searchTypeVO){
        Result result = new Result();
        ArrayList<StoreItemDTO> dtoArrayList = null;
        try {
            dtoArrayList = searchService.searchStoreList(searchTypeVO);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("搜索失败，请重试");
            return result;
        }
        result.success("搜索成功！",dtoArrayList);
        return result;
    }

    @GetMapping("/getGoodsDetails")
    public Result getGoodsDetails(@Param("goods_id") String goods_id){
        Result result = new Result();
        GoodsItemDTO goodsItemDTO = new GoodsItemDTO();
        try {
            goodsItemDTO = searchService.getGoodsItem(goods_id);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("获取失败");
            return result;
        }
        result.success("搜索成功！",goodsItemDTO);
        return result;
    }
}

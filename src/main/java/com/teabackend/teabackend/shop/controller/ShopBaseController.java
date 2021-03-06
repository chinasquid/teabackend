package com.teabackend.teabackend.shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.teabackend.teabackend.comment.Bean.Result;
import com.teabackend.teabackend.shop.bean.AddressItemDO;
import com.teabackend.teabackend.shop.bean.AddressItemVO;
import com.teabackend.teabackend.shop.bean.ShopCarItemDTO;
import com.teabackend.teabackend.shop.bean.ShopOrderItemVO;
import com.teabackend.teabackend.shop.service.ShopBaseService;
import com.teabackend.teabackend.userbase.bean.UserBodyDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-15 10:32
 */
@RestController
public class ShopBaseController {
    @Autowired
    private ShopBaseService shopBaseService;

    @PostMapping("/addToShopCar")
    public Result addToShopCar(@RequestBody ShopOrderItemVO shopOrderItemVO, HttpSession session){
        Result result = new Result();
        UserBodyDO userBodyDO = (UserBodyDO) session.getAttribute("user_body");
        if (null == userBodyDO){
            result.fail("登陆过期，请登录后重试");
            return result;
        }
        System.out.println("shopOrderItemVO:"+shopOrderItemVO);
        try {
            shopBaseService.setNewShopItem(shopOrderItemVO);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("添加失败，请稍后重试");
            return result;
        }
        result.success("添加成功!本次添加："+shopOrderItemVO.getNumber()+"个");
        return result;
    }

    @GetMapping("/getShoppingCarInformation")
    public Result getShoppingCarInformation(HttpSession session){
        Result result = new Result();
        UserBodyDO userBodyDO = (UserBodyDO) session.getAttribute("user_body");
        if (null == userBodyDO){
            result.fail("未登录，请先登录");
            return result;
        }
        ArrayList<ShopCarItemDTO> voArrayList = null;
        try {
            voArrayList = shopBaseService.getShoppingCarInformation(userBodyDO.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("获取购物车信息失败");
            return result;
        }
        result.success("搜索成功！",voArrayList);
        return result;
    }

    @PostMapping("/saveShopCarItem")
    public Result saveShopCarItem(@RequestBody ShopCarItemDTO shopCarItemDTO,HttpSession session){
        Result result = new Result();
        UserBodyDO userBodyDO = (UserBodyDO) session.getAttribute("user_body");
        if (null == userBodyDO){
            result.fail("未登录，请先登录");
            return result;
        }
        System.out.println("shopCarItemDTO:"+shopCarItemDTO);
        try {
            shopBaseService.setShopItemNumber(shopCarItemDTO);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("保存失败，请重试");
            return result;
        }
        result.success("保存成功");
        return result;
    }

    @PostMapping("/deleteShopCarItem")
    public Result deleteShopCarItem(@RequestBody JSONObject jsonObject, HttpSession session){
        Result result = new Result();
        UserBodyDO userBodyDO = (UserBodyDO) session.getAttribute("user_body");
        if (null == userBodyDO){
            result.fail("未登录，请先登录");
            return result;
        }
        Integer goods_id = (Integer) jsonObject.get("goods_id");
        System.out.println("goods_id:"+goods_id);
        try {
            shopBaseService.deleteShopCarItem(goods_id,userBodyDO.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("删除失败，请刷新后重试");
            return result;
        }
        result.success("保存成功");
        return result;
    }

    @PostMapping("/addNewAddress")
    public Result addNewAddress(@RequestBody AddressItemVO addressItemVO,HttpSession session){
        Result result = new Result();
        UserBodyDO userBodyDO = (UserBodyDO) session.getAttribute("user_body");
        if (null == userBodyDO){
            result.fail("请登录");
            return result;
        }
        try {
            shopBaseService.addNewAddress(addressItemVO);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("添加失败");
            return result;
        }
        result.success("添加成功");
        return result;
    }
    @GetMapping("/getAllAddress")
    public Result getAllAddress(HttpSession session){
        Result result = new Result();
        UserBodyDO userBodyDO = (UserBodyDO) session.getAttribute("user_body");
        if (null == userBodyDO){
            result.fail("请登录");
            return result;
        }
        ArrayList<AddressItemVO> addressItemVOS = new ArrayList<>();
        try {
            addressItemVOS = shopBaseService.getAllAddress(userBodyDO.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("获取收货地址失败");
            return result;
        }
        result.success("获取成功！",addressItemVOS);
        return result;
    }

    @PostMapping("/deletedAddress")
    public Result deletedAddress(@RequestBody AddressItemVO addressItemVO , HttpSession session){
        Result result = new Result();
        UserBodyDO userBodyDO = (UserBodyDO) session.getAttribute("user_body");
        Integer address_id = addressItemVO.getAddress_id();
        if (null == userBodyDO){
            result.fail("请登录");
            return result;
        }
        try {
            shopBaseService.deletedAddress(address_id);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("删除失败，请重试");
            return result;
        }
        result.success("删除成功!");
        return result;
    }

}

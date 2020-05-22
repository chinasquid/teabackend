package com.teabackend.teabackend.shop.service;

import com.teabackend.teabackend.shop.bean.AddressItemVO;
import com.teabackend.teabackend.shop.bean.ShopCarItemDTO;
import com.teabackend.teabackend.shop.bean.ShopOrderItemVO;
import com.teabackend.teabackend.shop.dao.ShopBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-15 10:33
 */
@Service
public class ShopBaseService {
    @Autowired
    private ShopBaseDao shopBaseDao;

    public Integer setNewShopItem(ShopOrderItemVO shopOrderItemVO) throws Exception {
        Integer num = shopBaseDao.selectShopOrderNum(shopOrderItemVO.getUser_id(),shopOrderItemVO.getGoods_id());
        if (null != num && num > 0){
            shopOrderItemVO.setNumber(shopOrderItemVO.getNumber()+num);
            shopBaseDao.updateNewShopItem(shopOrderItemVO);
        }else {
            shopBaseDao.insertNewShopItem(shopOrderItemVO);
        }
        return num;
    }

    public ArrayList<ShopCarItemDTO> getShoppingCarInformation(String user_id)throws Exception {
        return shopBaseDao.selectShoppingCarList(user_id);
    }

    public void deleteShopCarItem(Integer goods_id, String user_id) {
        shopBaseDao.deleteShopCarItem(goods_id,user_id);
    }


    public void setShopItemNumber(ShopCarItemDTO shopCarItemDTO) {
        shopBaseDao.updateShopItemNumber(shopCarItemDTO);
    }

    public void addNewAddress(AddressItemVO addressItemVO)throws Exception {
        shopBaseDao.addNewAddress(addressItemVO);
    }

    /**
     * 获取收货地址
     * @param user_id
     * @return
     */
    public ArrayList<AddressItemVO> getAllAddress(String user_id) {
        return shopBaseDao.getAllAddress(user_id);
    }

    /**
     * 删除单个地址
     * @param address_id
     */
    public void deletedAddress(Integer address_id) {
        shopBaseDao.deletedAddress(address_id);
    }
}

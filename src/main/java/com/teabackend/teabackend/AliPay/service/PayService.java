package com.teabackend.teabackend.AliPay.service;

import com.teabackend.teabackend.AliPay.bean.PayOrderItemDO;
import com.teabackend.teabackend.AliPay.dao.PayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-15 20:52
 */
@Service
public class PayService {
    @Autowired
    private PayDao payDao;

    public void saveNewOrder(PayOrderItemDO payOrderItemDO) {
        payDao.insertNewOrder(payOrderItemDO);
    }

    public void setOrderStatus(String outTradeNo, Integer status) {
        payDao.updateOrderStatus(outTradeNo,status);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setBpmStatus(String outTradeNo, Integer status, Integer bpmStatus) {
        payDao.updateOrderStatus(outTradeNo,status);
        payDao.setBpmStatus(outTradeNo,bpmStatus);
    }

    public ArrayList<String> getOrderList(String userId) {
        return payDao.selectWaitPayOrderList(userId);
    }
}

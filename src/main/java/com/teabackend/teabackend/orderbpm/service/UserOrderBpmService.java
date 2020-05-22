package com.teabackend.teabackend.orderbpm.service;

import com.teabackend.teabackend.orderbpm.bean.FinishOrderDO;
import com.teabackend.teabackend.orderbpm.bean.WaitDeliverDO;
import com.teabackend.teabackend.orderbpm.bean.WaitPayDO;
import com.teabackend.teabackend.orderbpm.bean.WaitReceiptDO;
import com.teabackend.teabackend.orderbpm.dao.UserOrderBpmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-17 13:21
 */
@Service
public class UserOrderBpmService {
    @Autowired
    private UserOrderBpmDao bpmDao;

    public ArrayList<WaitPayDO> getWaitPayOrder(String user_id)throws RuntimeException {
        return bpmDao.getWaitPayOrder(user_id);
    }

    public ArrayList<WaitDeliverDO> getWaitDeliverOrder(String user_id)throws RuntimeException {
        return bpmDao.getWaitDeliverOrder(user_id);
    }

    public ArrayList<WaitReceiptDO> getWaitReceiptOrder(String user_id) {
        return bpmDao.getWaitReceiptOrder(user_id);
    }

    public ArrayList<FinishOrderDO> getFinallyOrder(String user_id) {
        return bpmDao.getFinallyOrder(user_id);
    }

    public void confirmReceipt(String out_trade_no, String userId, String day, String time) {
        bpmDao.confirmReceipt(out_trade_no,userId,day,time);
    }
}

package com.teabackend.teabackend.orderbpm.controller;

import com.teabackend.teabackend.AliPay.controller.PayDoController;
import com.teabackend.teabackend.comment.Bean.Result;
import com.teabackend.teabackend.orderbpm.bean.*;
import com.teabackend.teabackend.orderbpm.service.UserOrderBpmService;
import com.teabackend.teabackend.userbase.bean.UserBodyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-17 13:21
 */
@RestController
public class UserOrderBpmController {

    public static final SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private UserOrderBpmService bpmService;
    @Autowired
    private PayDoController payDoController;

    @GetMapping("/getWaitPayOrder")
    public Result getWaitPayOrder(HttpSession session) {
        Result result = new Result();
        UserBodyDO user_body = (UserBodyDO) session.getAttribute("user_body");
        if (null == user_body) {
            result.fail("未登录，请登录后重试");
            return result;
        }
        ArrayList<WaitPayDO> list = null;
        try {
            payDoController.queryList(user_body.getUser_id());
            Thread.sleep(1000);
            list = bpmService.getWaitPayOrder(user_body.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("获取失败！");
            return result;
        }
        result.success("获取成功！", list);
        return result;
    }

    @GetMapping("/getWaitDeliverOrder")
    public Result getWaitDeliverOrder(HttpSession session) {
        System.out.println("获取待发货信息");
        Result result = new Result();
        UserBodyDO user_body = (UserBodyDO) session.getAttribute("user_body");
        if (null == user_body) {
            result.fail("未登录，请登录后重试");
            return result;
        }
        ArrayList<WaitDeliverDO> waitDeliverDOS = new ArrayList<>();
        try {
            waitDeliverDOS = bpmService.getWaitDeliverOrder(user_body.getUser_id());
        } catch (RuntimeException e) {
            e.printStackTrace();
            result.fail("获取待发货失败！");
            return result;
        }
        result.success("获取待发货订单成功！",waitDeliverDOS);
        return result;
    }

    @GetMapping("/getWaitReceiptOrder")
    public Result getWaitReceiptOrder(HttpSession session) {
        System.out.println("获取待收货信息");
        Result result = new Result();
        UserBodyDO user_body = (UserBodyDO) session.getAttribute("user_body");
        if (null == user_body) {
            result.fail("未登录，请登录后重试");
            return result;
        }
        ArrayList<WaitReceiptDO> waitReceiptDOS = new ArrayList<>();
        try {
            waitReceiptDOS = bpmService.getWaitReceiptOrder(user_body.getUser_id());
        } catch (RuntimeException e) {
            e.printStackTrace();
            result.fail("获取待收货信息失败！");
            return result;
        }
        result.success("获取待收货信息成功！",waitReceiptDOS);
        return result;
    }

    @PostMapping("/confirmReceipt")
    public Result confirmReceipt(@RequestBody ConfirmReceiptVO confirmReceiptVO, HttpSession session){
        System.out.println("获取已完成的订单信息");
        Result result = new Result();
        UserBodyDO user_body = (UserBodyDO) session.getAttribute("user_body");
        if (null == user_body) {
            result.fail("未登录，请登录后重试");
            return result;
        }
        Date date = new Date();
        String day = dayFormat.format(date);
        String time = timeFormat.format(date);
        try {
            bpmService.confirmReceipt(confirmReceiptVO.getOut_trade_no(),user_body.getUser_id(),day,time);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("确认收货失败");
            return result;
        }
        result.success("确认收货成功!");
        return result;
    }

    @GetMapping("/getFinishOrder")
    public Result getFinallyOrder(HttpSession session) {
        System.out.println("获取已完成的订单信息");
        Result result = new Result();
        UserBodyDO user_body = (UserBodyDO) session.getAttribute("user_body");
        if (null == user_body) {
            result.fail("未登录，请登录后重试");
            return result;
        }
        ArrayList<FinishOrderDO> finishOrderDOS = new ArrayList<>();
        try {
            finishOrderDOS = bpmService.getFinallyOrder(user_body.getUser_id());
        } catch (RuntimeException e) {
            e.printStackTrace();
            result.fail("获取已完成订单信息失败！");
            return result;
        }
        result.success("获取已完成订单成功！", finishOrderDOS);
        return result;
    }

}

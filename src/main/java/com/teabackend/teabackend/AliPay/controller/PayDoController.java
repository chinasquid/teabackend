package com.teabackend.teabackend.AliPay.controller;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.Data;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.utils.Utils;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.teabackend.teabackend.AliPay.bean.PayOrderItemDO;
import com.teabackend.teabackend.AliPay.bean.PayParamVO;
import com.teabackend.teabackend.AliPay.service.PayService;
import com.teabackend.teabackend.comment.Bean.Result;
import com.teabackend.teabackend.userbase.bean.UserBodyDO;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static com.teabackend.teabackend.AliPay.config.PayConfig.tradeService;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-15 18:21
 */
@RestController
public class PayDoController {


    private static Log log = LogFactory.getLog(PayDoController.class);
    private static String DirPath = "D:/tea/QRCode/";

    @Autowired
    private PayService payService;

    /**
     * 创建预付款订单
     *
     * @param payParamVO
     * @param session
     * @return
     * @throws IOException
     */
    @PostMapping("/pay")
    public Result pay(@RequestBody PayParamVO payParamVO, HttpSession session) throws IOException {
        Result result = new Result();
        //判断登录
        UserBodyDO user_body = (UserBodyDO) session.getAttribute("user_body");
        if (null == user_body) {
            result.fail("登录过期。请重新登录");
            return result;
        }
        //获取基础信息，创建阿里支付订单
        System.out.println("payParamVO:" + payParamVO);
        String outTradeNo = UUID.randomUUID().toString().replace("-", "_");
        String subject = payParamVO.getStore_name() + "门店" + payParamVO.getGoods_tittle() + "商品购买";
        String body = "购买" + payParamVO.getGoods_tittle() + "商品购买：" + payParamVO.getNumber() + "件，单价：" + payParamVO.getPrice() + "元,邮费：" + payParamVO.getPost() + "元，总价：" + payParamVO.getAll_price();
        String storeId = payParamVO.getStore_id();
        String totalAmount = payParamVO.getAll_price().toString();
        String userId = payParamVO.getUser_id();

        //获取订单创建的时间
        Date t = new Date();
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String day = dayFormat.format(t);
        String time = timeFormat.format(t);
        System.out.println("day:"+day+"::::::time:"+time);
        //单个商品信息
        GoodsDetail goodsDetail = GoodsDetail.newInstance(payParamVO.getGoods_id(), payParamVO.getGoods_tittle(), payParamVO.getAll_price().longValue(), payParamVO.getNumber());
        ArrayList<GoodsDetail> goodsDetails = new ArrayList<GoodsDetail>();
        goodsDetails.add(goodsDetail);

        //创建本地订单信息
        PayOrderItemDO payOrderItemDO = new PayOrderItemDO();
        payOrderItemDO.setOut_trade_no(outTradeNo);
        payOrderItemDO.setSeller_id("2088102180619446");
        payOrderItemDO.setTotal_amount(totalAmount);
        payOrderItemDO.setBody(body);
        payOrderItemDO.setSubject(subject);
        payOrderItemDO.setStore_id(storeId);
        payOrderItemDO.setUser_id(userId);
        payOrderItemDO.setGoods_id(payParamVO.getGoods_id());
        payOrderItemDO.setGoods_tittle(payParamVO.getGoods_tittle());
        payOrderItemDO.setPrice(payParamVO.getPrice());
        payOrderItemDO.setNumber(payParamVO.getNumber());
        payOrderItemDO.setPost(payParamVO.getPost());
        payOrderItemDO.setStore_name(payParamVO.getStore_name());
        payOrderItemDO.setPayType(payParamVO.getPayType());
        payOrderItemDO.setReceiving_address(payParamVO.getReceiving_address());
        payOrderItemDO.setCreate_day(day);
        payOrderItemDO.setCreate_time(time);

        //保存订单信息在支付宝类中
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setOutTradeNo(outTradeNo)
                .setSellerId(payOrderItemDO.getSeller_id())
                .setSubject(payOrderItemDO.getSubject())
                .setTotalAmount(payOrderItemDO.getTotal_amount())
                .setStoreId(payOrderItemDO.getStore_id())
                .setBody(payOrderItemDO.getBody())
                .setTimeoutExpress("5m")
                .setGoodsDetailList(goodsDetails);

        log.info("订单创建："+outTradeNo);
        //将订单信息发送出去，获得返回信息
        AlipayF2FPrecreateResult payResult = tradeService.tradePrecreate(builder);

        //返回信息判断，并插入本地订单信息
        switch (payResult.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功: )");
                AlipayTradePrecreateResponse response = payResult.getResponse();
                dumpResponse(response);
                // 需要修改为运行机器上的路径
                //二维码保存在本地，然后代用即可扫码
                String filePath = String.format("D:/tea/QRCode/qr-%s.png",
                        response.getOutTradeNo());
                String virtualPath = filePath.substring(7);
                log.info("filePath:" + filePath);
                log.info("virtualPath:" + virtualPath);
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                payOrderItemDO.setQr_path(virtualPath);
                payOrderItemDO.setOrder_status("2");
                payService.saveNewOrder(payOrderItemDO);
                result.success("下单成功！请在5分钟内支付！", virtualPath);
                break;
            case FAILED:
                log.error("支付宝预下单失败!!!");
                result.fail("支付宝预下单失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，预下单状态未知!!!");
                result.fail("系统异常，预下单状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                result.fail("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return result;
    }


    /**
     * 查询订单
     */
    @GetMapping("/payQuery")
    public void test_trade_query(@Param("outTradeNo") String outTradeNo) {
        log.info("订单查询："+outTradeNo);
        // (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
//        String outTradeNo = "tradepay14817938139942440181";

        // 创建查询请求builder，设置请求参数
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
                .setOutTradeNo(outTradeNo);

        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("查询返回该订单支付成功: )");

                AlipayTradeQueryResponse response = result.getResponse();
                dumpResponse(response);

                log.info(response.getTradeStatus());
                if (Utils.isListNotEmpty(response.getFundBillList())) {
                    for (TradeFundBill bill : response.getFundBillList()) {
                        log.info(bill.getFundChannel() + ":" + bill.getAmount());
                    }
                }
                payService.setBpmStatus(outTradeNo, 1, 2);
                break;

            case FAILED:
                log.error("查询返回该订单支付失败或被关闭!!!");
                payService.setOrderStatus(outTradeNo, 2);

                break;

            case UNKNOWN:
                log.error("系统异常，订单支付状态未知!!!");
                payService.setOrderStatus(outTradeNo, 0);
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                payService.setOrderStatus(outTradeNo, 4);
                break;
        }
    }

    public void queryList(String userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> list = payService.getOrderList(userId);
                for (String outTradeNo : list) {
                    test_trade_query(outTradeNo);
                }
            }
        });
        thread.start();
    }

    /**
     * 退款
     */
    public void test_trade_refund() {

        // (必填) 外部订单号，需要退款交易的商户外部订单号
        String outTradeNo = "tradepay14817938139942440181";

        // (必填) 退款金额，该金额必须小于等于订单的支付金额，单位为元
        String refundAmount = "0.01";

        // (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
        // 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
        String outRequestNo = "";

        // (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计
        String refundReason = "正常退款，用户买多了";

        // (必填) 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
        String storeId = "test_store_id";

        // 创建退款请求builder，设置请求参数
        AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
                .setOutTradeNo(outTradeNo).setRefundAmount(refundAmount).setRefundReason(refundReason)
                .setOutRequestNo(outRequestNo).setStoreId(storeId);

        AlipayF2FRefundResult result = tradeService.tradeRefund(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝退款成功: )");
                break;

            case FAILED:
                log.error("支付宝退款失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，订单退款状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
    }

    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }


}

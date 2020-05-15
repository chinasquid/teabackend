package com.teabackend.teabackend.business.controller;

import com.teabackend.teabackend.business.bean.GoodsItemDo;
import com.teabackend.teabackend.business.bean.GoodsListItemDO;
import com.teabackend.teabackend.business.bean.GoodsSearchOption;
import com.teabackend.teabackend.business.bean.ProductItemVO;
import com.teabackend.teabackend.business.service.BusinessProductService;
import com.teabackend.teabackend.comment.Bean.Result;
import com.teabackend.teabackend.userbase.bean.UserBodyDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static com.teabackend.teabackend.config.CROSConfig.systemPath;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-12 15:30
 */
@RestController
public class BusinessProductController {
    private String userFilePath = "business/";

    @Autowired
    private BusinessProductService productService;

    @PostMapping("/UploadProductImg/{img_id}")
    @CrossOrigin
    public Result UploadProductImg(@Param("file") MultipartFile file, HttpSession session, @PathVariable("img_id") String img_id) {
        Result result = new Result();
        String fileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString().substring(0, 8) + fileName.substring(fileName.indexOf('.'));
        UserBodyDO user_body = (UserBodyDO) session.getAttribute("user_body");
        if (null == user_body) {
            result.fail("请登录");
            return result;
        }
        //检查是否含有暂存图片，防止重复上传图片
        productService.checkTempImg(user_body.getUser_id(), img_id);
        String fileDirPath = user_body.getUser_id() + "/temp/";
        String fileRealPath = systemPath + userFilePath + fileDirPath;
        String fileVirtualPath = userFilePath + fileDirPath + newFileName;
        File dir = new File(fileRealPath);
        if (!dir.exists()) {
            System.out.println("开始创建文件夹");
            dir.mkdirs();
        }
        System.out.println("开始创建文件");
        //文件存放路径
        fileRealPath = fileRealPath + newFileName;
        File realFile1 = new File(fileRealPath);
        String user_id = user_body.getUser_id();
        try {
            file.transferTo(realFile1);
            productService.saveGoodsTempPath(fileRealPath, fileVirtualPath, user_id, img_id);
        } catch (IOException e) {
            e.printStackTrace();
            result.fail("图片上传失败，请重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }
        result.success("图片上传成功！");
        return result;
    }

    @PostMapping("/uploadProductOtherImg/{timeNow}")
    public Result uploadProductOtherImg(@Param("file") MultipartFile[] files, HttpSession session, @PathVariable("timeNow") String data) {
        System.out.println("开始接收多个文件");
        Result result = new Result();
        for (MultipartFile file : files) {
            System.out.println("name:#####" + file.getOriginalFilename());
        }
        return result;
    }


    @PostMapping("/creatGoodsItem")
    public Result creatProductItem(@RequestBody ProductItemVO productItemVO) {
        Result result = new Result();
        System.out.println("productItemVO:" + productItemVO);
        try {
            productService.saveGoodsTempDetails(productItemVO);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("创建失败");
            return result;
        }
        result.success("创建成功！");
        return result;
    }

    @GetMapping("/getGoodsList")
    public Result getGoodsList(HttpSession session) {
        Result result = new Result();
        UserBodyDO user_body = (UserBodyDO) session.getAttribute("user_body");
        if (null == user_body) {
            result.fail("请重新登录！");
            return result;
        }
        ArrayList<GoodsItemDo> goodsItemDoArrayList = null;
        try {
            goodsItemDoArrayList = productService.getGoodsList(user_body.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("获取失败！");
            return result;
        }
        result.success("获取成功！", goodsItemDoArrayList);
        return result;
    }

    @PostMapping("/getAllGoodsList")
    public Result getAllGoodsList(@RequestBody GoodsSearchOption searchOption){
        Result result = new Result();
        ArrayList<GoodsListItemDO> goodsList = null;
        try {
            goodsList = productService.getAllGoodsList(searchOption);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("获取失败，请重试");
            return result;
        }
        result.success("获取成功！",goodsList);
        return result;
    }
}

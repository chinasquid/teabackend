package com.teabackend.teabackend.business.controller;

import com.teabackend.teabackend.business.bean.BusinessAllInformationDTO;
import com.teabackend.teabackend.business.service.BusinessBaseService;
import com.teabackend.teabackend.comment.Bean.Result;
import com.teabackend.teabackend.userbase.bean.UserBodyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.teabackend.teabackend.config.CROSConfig.systemPath;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 12:57
 */
@RestController
public class BusinessBaseController {

    private String userFilepath = "business/";
    private String backDirPath = "backImg/";
    @Autowired
    private BusinessBaseService baseService;
    @GetMapping("/getBusinessInformation")
    public Result getBusinessInformation(HttpSession session){
        Result result = new Result();
        UserBodyDO user_body = (UserBodyDO) session.getAttribute("user_body");
        if (null == user_body){
            result.fail("未登录，请重新登录");
            return result;
        }
        BusinessAllInformationDTO allInformationDTO = new BusinessAllInformationDTO();
        try {
            allInformationDTO = baseService.getBusinessInAllformation(user_body);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("获取失败！");
            return result;
        }
        result.success("获取成功",allInformationDTO);
        return result;
    }

    @PostMapping("/setStoreInformation")
    public Result setStoreInformation(@RequestBody BusinessAllInformationDTO allInformationDTO,HttpSession session){
        Result result = new Result();
        UserBodyDO userBodyDO = (UserBodyDO) session.getAttribute("user_body");
        if (null == userBodyDO){
            result.fail("登录过期，请登录后重试");
            return result;
        }
        System.out.println("userBodyDO:"+userBodyDO);
        String user_name = userBodyDO.getUser_name();
        BusinessAllInformationDTO newAllInformationDTO = new BusinessAllInformationDTO();
        allInformationDTO.setUser_id(userBodyDO.getUser_id());
        try {
            newAllInformationDTO = baseService.setInformation(allInformationDTO);
            if (null == newAllInformationDTO){
                result.fail("更新失败，请刷新重试！");
                return result;
            }
            newAllInformationDTO.setUser_name(user_name);
            newAllInformationDTO.setPhone(userBodyDO.getPhone());
            newAllInformationDTO.setHead_img(userBodyDO.getHead_img());
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("更新失败，请重试！");
            return result;
        }

        result.success("更新成功！",newAllInformationDTO);
        return result;
    }


    @PostMapping("/uploadStoreBackImg")
    public Result uploadStoreBackImg(MultipartFile file,HttpSession session){
        System.out.println("开始上传背景图");
        Result result = new Result();
        String fileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString().substring(0, 8) + fileName.substring(fileName.indexOf('.'));
        UserBodyDO user_body = (UserBodyDO) session.getAttribute("user_body");
        if (null == user_body) {
            result.fail("请登录");
            return result;
        }
        String fileVirtualPath = userFilepath+user_body.getUser_id()+backDirPath+newFileName;
        String fileRealPath = systemPath+userFilepath+user_body.getUser_id()+backDirPath;
        File DIR = new File(fileRealPath);
        if (!DIR.exists()){
            System.out.println("创建文件夹");
            DIR.mkdirs();
        }
        fileRealPath = fileRealPath+newFileName;
        File file1 = new File(fileRealPath);
        try {
            file.transferTo(file1);
            baseService.saveBackImgPath(fileRealPath,fileVirtualPath,user_body.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("上传失败！请刷新重试！");
            return result;
        }
        result.success("上传成功！",fileVirtualPath);
        return result;
    }
}

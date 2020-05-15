package com.teabackend.teabackend.business.service;

import com.teabackend.teabackend.business.bean.BusinessAllInformationDTO;
import com.teabackend.teabackend.business.dao.BusinessBaseDao;
import com.teabackend.teabackend.userbase.bean.UserBodyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-14 13:56
 */
@Service
public class BusinessBaseService {

    @Autowired
    private BusinessBaseDao baseDao;

    /**
     * 获取店铺所有信息
     *
     * @param user_body 用户基础信息
     * @return 店铺所有信息
     */
    public BusinessAllInformationDTO getBusinessInAllformation(UserBodyDO user_body) throws Exception {
        BusinessAllInformationDTO allInformationDTO = new BusinessAllInformationDTO();
        allInformationDTO = baseDao.selectStoreInformation(user_body.getUser_id(),user_body.getPhone());
        allInformationDTO.setUser_name(user_body.getUser_name());
        allInformationDTO.setPhone(user_body.getPhone());
        allInformationDTO.setHead_img(user_body.getHead_img());
        return allInformationDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public BusinessAllInformationDTO setInformation(BusinessAllInformationDTO allInformationDTO) {
        BusinessAllInformationDTO dto = new BusinessAllInformationDTO();
        try {
            baseDao.updateInformation(allInformationDTO);
            dto = baseDao.selectStoreInformation(allInformationDTO.getUser_id(),allInformationDTO.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("dto:"+dto);
        return dto;
    }

    /**
     * 保存店铺背景图片路径
     * @param fileRealPath 文件真实位置
     * @param fileVirtualPath 文件虚拟访问位置
     * @param user_id 用户ID
     */
    public void saveBackImgPath(String fileRealPath, String fileVirtualPath, String user_id) {
        try {
            baseDao.saveBackImgPath(fileRealPath,fileVirtualPath,user_id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("保存路径失败");
        }
    }
}

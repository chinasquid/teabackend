package com.teabackend.teabackend.userbase.service;

import com.teabackend.teabackend.search.bean.GoodsItemDTO;
import com.teabackend.teabackend.userbase.bean.UserBodyDO;
import com.teabackend.teabackend.userbase.bean.UserRegisterDO;
import com.teabackend.teabackend.userbase.bean.UserRegisterVO;
import com.teabackend.teabackend.userbase.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.UUID;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-11 12:52
 */
@Service
public class BaseService {

    @Autowired
    private BaseDao baseDao;


    /**
     * 检测账户是否已经注册过。注册过为true
     *
     * @param phone 手机号
     * @return
     */
    public boolean haveUserAccount(String phone) {
        Integer AccountNum = baseDao.selectAccountNum(phone);
        if (AccountNum > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检测账户是否已经注册过。注册过为true
     *
     * @param phone 手机号
     * @return
     */
    public boolean haveUserAccount(String phone, String password) {
        Integer AccountNum = baseDao.selectHaveAccountNum(phone, password);
        if (AccountNum > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 开始注册
     *
     * @param userRegisterVO 注册时的账号信息
     * @return 成功返回true，则继续操作，否则false，注册失败
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean doRegister(UserRegisterVO userRegisterVO) {
        try {
            UserRegisterDO userRegisterDO = new UserRegisterDO();
            System.out.println("获取uuid");
            userRegisterDO.setUser_id(getUUIDNum());
            userRegisterDO.setUser_name(userRegisterVO.getUser_name());
            userRegisterDO.setPhone(userRegisterVO.getPhone());
            userRegisterDO.setPassword(userRegisterVO.getPassword());
            userRegisterDO.setUser_type(1);
            System.out.println("开始注册");
            baseDao.insertRegisterAccount(userRegisterDO);
            baseDao.insertRegisterInformation(userRegisterDO);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("注册失败");
            return false;
        }
        System.out.println("注册成功");
        return true;
    }

    public String getUUIDNum() {
        int num = 1;
        String code = "";
        do {
            int UUIDHashCode = UUID.randomUUID().toString().hashCode();
            //有可能是负数
            if (UUIDHashCode < 0) {
                UUIDHashCode = -UUIDHashCode;
            }
            code = (UUIDHashCode + "").substring(0, 6);
            num = baseDao.selectIdNum(code);
        } while (num != 0);
        return code;
    }


    /**
     * 获取用户基础信息
     *
     * @param phone
     * @return
     */
    public UserBodyDO getUserBody(String phone) {
        UserBodyDO userBodyDO = new UserBodyDO();
        userBodyDO = baseDao.selectUserBody(phone);
        return userBodyDO;
    }

    /**
     * 升级为商家
     * @param userBodyDO 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean levelUp(UserBodyDO userBodyDO) {
        try {
            baseDao.UpdateUserType(userBodyDO.getPhone());
            baseDao.insertStoreBody(userBodyDO.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("升级失败");
            return false;
        }
        return true;
    }
}

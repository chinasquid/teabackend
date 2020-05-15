package com.teabackend.teabackend.userbase.controller;

import com.teabackend.teabackend.comment.Bean.Result;
import com.teabackend.teabackend.userbase.bean.UserBodyDO;
import com.teabackend.teabackend.userbase.bean.UserLoginVO;
import com.teabackend.teabackend.userbase.bean.UserRegisterVO;
import com.teabackend.teabackend.userbase.service.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author shuyang
 * @Description: 登录，注册，检测是否登录
 * @create 2020-04-20 14:16
 */
@Api("登录注册、基础信息接口")
@RestController
public class BaseController {

    @Autowired
    private BaseService baseService;

    @ApiOperation(value = "用户是否登录", notes = "根据id查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
    @GetMapping("/isLogin")
    public Result isLogin(HttpServletRequest request , HttpServletResponse response) {
        Result result = new Result();
        HttpSession session = request.getSession();
        if (session.getAttribute("user_body") == null) {
            result.fail("未登录");
            return result;
        }
        UserBodyDO userBodyDO = (UserBodyDO) session.getAttribute("user_body");
        result.success("用户已登录", userBodyDO);
        return result;
    }

    @GetMapping("/signOut")
    public Result signOut(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        HttpSession session = request.getSession();
        session.removeAttribute("user_body");
        result.success("退出成功！");
        return result;
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterVO userRegisterVO) {
        Result result = new Result();
        if (baseService.haveUserAccount(userRegisterVO.getPhone())) {
            result.fail("该用户已注册，请直接登录");
            System.out.println("该用户已注册，请直接登录：" + result);
            return result;
        }
        try {
            if (baseService.doRegister(userRegisterVO)) {
                result.success("注册成功！");
                ;
                return result;
            } else {
                result.fail("注册失败！");
                return result;
            }
        } catch (Exception e) {
            System.out.println("注册失败，请重试！");
            result.fail("注册失败，请重试！");
        }
        return result;
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginVO userLoginVO, HttpServletRequest request) {
        Result result = new Result();
        if (!baseService.haveUserAccount(userLoginVO.getPhone(), userLoginVO.getPassword())) {
            result.fail("用户不存在请注册");
            return result;
        }
        UserBodyDO userBodyDO = new UserBodyDO();
        try {
            userBodyDO = baseService.getUserBody(userLoginVO.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("登录失败");
            return result;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user_body", userBodyDO);
        result.success("登录成功", userBodyDO);
        return result;
    }

    @GetMapping("/levelUp")
    public Result levelUp(HttpServletRequest request){
        System.out.println("开始升级");
        UserBodyDO user_body = (UserBodyDO) request.getSession().getAttribute("user_body");
        Result result = new Result();
        try {
            if (!baseService.levelUp(user_body)){
                result.fail("升级失败！请重试");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("升级失败！");
            return result;
        }
        result.success("升级成功！");
        return result;
    }

    @GetMapping("/getBaseMsg")
    public Result getBaseMsg(@Param("") String user){
        Result result = new Result();
        return result;
    }
}

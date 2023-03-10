package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.common.exception.AdminException;
import com.junmooo.springbootdemo.entity.token.UserToken;
import com.junmooo.springbootdemo.entity.user.User;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.user.UserService;
import com.junmooo.springbootdemo.utils.RedisUtils;
import com.junmooo.springbootdemo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    public JSONObject login(@RequestBody User user) {
        try {
            User retUser = userService.getUserByName(user.getName());

            if (retUser == null) {
                return CommonResponse.fail(ErrorCode.LOGINFAIL, "用户名或密码错误");
            }
            if (retUser.getDeleteFlag().equals("0")) {
                return CommonResponse.fail(ErrorCode.LOGINFAIL, "用户已注销");
            }
            if (!user.getPwd().equals(retUser.getPwd())) {
                return CommonResponse.fail(ErrorCode.LOGINFAIL, "用户名或密码错误");
            }
            String token = TokenUtils.generateUserToken(UserToken.builder().id(retUser.getId()).name(retUser.getName()).email(retUser.getEmail()).avatar(retUser.getAvatar()).build(), 60);
            RedisUtils.set(token, JSONObject.toJSONString(retUser), 60, TimeUnit.MINUTES);
            JSONObject res = new JSONObject();
            res.put("user", retUser);
            res.put("token", token);
            return CommonResponse.success(res);
        } catch (AdminException e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.WRONGTOKEN, "token 失效");
        } catch (Exception e) {
            System.out.println(e);
            return CommonResponse.fail(ErrorCode.SQLERR, "登陆异常");
        }
    }
    @GetMapping("getName")
    public JSONObject getName(@RequestParam String name) {
        try {
            return CommonResponse.success(userService.getUserByName(name));
        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SQLERR, "user insert err");
        }
    }

    @PostMapping("register")
    public JSONObject register(@RequestBody User user) {
        try {
            User retUser = userService.add(user);
            JSONObject res = CommonResponse.success(retUser);
            return res;
        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SQLERR, "user insert err");
        }
    }
}

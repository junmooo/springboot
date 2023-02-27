package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.atical.Article;
import com.junmooo.springbootdemo.entity.token.UserToken;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.article.ArticleService;
import com.junmooo.springbootdemo.service.file.FileService;
import com.junmooo.springbootdemo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //单个文件的上传
    @PostMapping("/save")
    public JSONObject save(@RequestBody Article article, HttpServletRequest request) {
        UserToken userToken = (UserToken) request.getAttribute("userToken");
        try {
            return CommonResponse.success(articleService.save(article, userToken));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.WRONGTOKEN, "save 失败");
    }

    @GetMapping("/all")
    public JSONObject all() {
        try {
            return CommonResponse.success(articleService.all());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.WRONGTOKEN, "save 失败");
    }

    @GetMapping("/del")
    public JSONObject del(@RequestParam String id) {
        try {
            if (articleService.del(id) == 1) {
                return CommonResponse.success("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.WRONGTOKEN, "del 失败");
    }
}

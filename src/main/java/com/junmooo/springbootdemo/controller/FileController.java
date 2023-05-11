package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;

import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.file.FileService;
import com.junmooo.springbootdemo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("file")
@CrossOrigin(origins = "*")
public class FileController {

    @Autowired
    private FileService fileService;

    //单个文件的上传
    @PostMapping("/upload")
    public JSONObject upload(MultipartFile file, HttpServletRequest request) {
        if (file == null) {
            return CommonResponse.fail(ErrorCode.PARAMERR, "文件不能为空");
        }
        String token = "";
        try {
            token = TokenUtils.getInfoFromUserToken(request.getHeader("token")).getId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            return CommonResponse.success(fileService.save(file, token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "上传失败");
    }

    //多个文件的上传
    @PostMapping("/uploads")
    public JSONObject uploads(MultipartFile[] files, HttpServletRequest request) {
        if (files == null || files.length < 1) {
            return CommonResponse.fail(ErrorCode.PARAMERR, "文件不能为空");
        }
        String token = "";
        try {
            token = TokenUtils.getInfoFromUserToken(request.getHeader("token")).getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return CommonResponse.success(fileService.saveAll(files, token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "上传失败");
    }

    @PostMapping("/store")
    public JSONObject uploads(MultipartFile file) {
        try {
            return CommonResponse.success(fileService.store(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "上传失败");
    }

    @PostMapping("/delStore")
    public JSONObject del(String fileName) {
        try {
            return CommonResponse.success(fileService.del(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "删除失败");
    }

}

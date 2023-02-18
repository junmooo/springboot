package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;

import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    //单个文件的上传
    @PostMapping("/upload")
    public JSONObject upload(MultipartFile file) {
        try {
            return CommonResponse.success(fileService.save(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResponse.success("上传失败");
    }

    //多个文件的上传
    @PostMapping("/uploads")
    public JSONObject uploads(MultipartFile[] files) {
        //1，对文件数组做判空操作
        if (files == null || files.length < 1) {
            return CommonResponse.fail(ErrorCode.PARAMERR, "文件不能为空");
        }
        try {
            return CommonResponse.success(fileService.saveAll(files));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "上传失败");
    }

}

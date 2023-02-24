package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;

import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.file.FileService;
import com.junmooo.springbootdemo.utils.TokenUtils;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    //单个文件的上传
    @PostMapping("/upload")
    public JSONObject upload(MultipartFile file, HttpServletRequest request) {
        try {
            return CommonResponse.success(fileService.save(file, TokenUtils.getInfoFromToken(request.getHeader("token")).getOperId()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JoseException e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.WRONGTOKEN, "token 解析失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "上传失败");
    }

    //多个文件的上传
    @PostMapping("/uploads")
    public JSONObject uploads(MultipartFile[] files, HttpServletRequest request) {
        //1，对文件数组做判空操作
        if (files == null || files.length < 1) {
            return CommonResponse.fail(ErrorCode.PARAMERR, "文件不能为空");
        }
        try {
            return CommonResponse.success(fileService.saveAll(files, TokenUtils.getInfoFromToken(request.getHeader("token")).getOperId()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JoseException e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.WRONGTOKEN, "token 解析失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "上传失败");
    }

}

package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.photo.Album;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.photo.AlbumService;
import com.junmooo.springbootdemo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/album")
@CrossOrigin(origins = "*")
public class AlbumController {
    @Autowired
    private AlbumService photoAlbumService;

    @PostMapping("/add")
    public JSONObject add(@RequestBody Album album, HttpServletRequest request) {
        try {
            String operId = TokenUtils.getInfoFromOperToken(request.getHeader("token")).getOperId();
            album.setUploaderId(operId);
            return CommonResponse.success(photoAlbumService.save(album));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "add失败");
    }

    @PostMapping("/update")
    public JSONObject update(@RequestBody Album album, HttpServletRequest request) {
        try {
            String operId = TokenUtils.getInfoFromOperToken(request.getHeader("token")).getOperId();
            album.setUploaderId(operId);
            return CommonResponse.success(photoAlbumService.update(album));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "update失败");
    }

    @GetMapping("/hide")
    public JSONObject hide(int id, HttpServletRequest request) {
        try {
            return CommonResponse.success(photoAlbumService.hide(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "hide 失败");
    }

    @GetMapping("/activate")
    public JSONObject activate(int id, HttpServletRequest request) {
        try {
            return CommonResponse.success(photoAlbumService.activate(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "hide 失败");
    }


    @GetMapping("/queryByPage")
    public JSONObject queryByPage(String keyword,int pgNum, int pgSize) {
        try {
            return CommonResponse.success(photoAlbumService.queryByPage(keyword,pgNum,pgSize));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "上传失败");
    }

    @GetMapping("/queryAll")
    public JSONObject queryAll() {
        try {
            return CommonResponse.success(photoAlbumService.queryAll());
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
//            return CommonResponse.success(fileService.saveAll(files, token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "上传失败");
    }
}

package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.photo.Album;
import com.junmooo.springbootdemo.entity.photo.Photo;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.photo.PhotoService;
import com.junmooo.springbootdemo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/photo")
@CrossOrigin(origins = "*")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping("/queryByAlbum")
    public JSONObject queryByAlbum(int album, HttpServletRequest request) {
        try {
            return CommonResponse.success(photoService.queryByAlbum(album));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "queryByAlbum fail");
    }

    @GetMapping("/deleteById")
    public JSONObject deleteById(String id,String fileName, HttpServletRequest request) {
        try {
            return CommonResponse.success(photoService.delete(id,fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "queryByAlbum fail");
    }

    @PostMapping("/addPhotos")
    public JSONObject add(Photo photo, MultipartFile[] files, HttpServletRequest request) {
        try {
            String operId = TokenUtils.getInfoFromOperToken(request.getHeader("token")).getOperId();
            photo.setUploaderId(operId);
            return CommonResponse.success(photoService.saveAll(photo,files));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "add失败");
    }

    @PostMapping("/updatePhoto")
    public JSONObject update(@RequestBody Photo photo, HttpServletRequest request) {
        try {
            return CommonResponse.success(photoService.update(photo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "add失败");
    }

    @PostMapping("/addPhoto")
    public JSONObject add(Photo photo, MultipartFile file, HttpServletRequest request) {
        try {
            String operId = TokenUtils.getInfoFromOperToken(request.getHeader("token")).getOperId();
            photo.setUploaderId(operId);
            return CommonResponse.success(photoService.save(photo,file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "add失败");
    }
}

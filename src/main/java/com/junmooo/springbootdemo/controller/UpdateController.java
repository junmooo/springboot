package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.dict.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("update")
@CrossOrigin(origins = "*")
public class UpdateController {

    @Autowired
    private DictService dictService;

    @GetMapping("getLatestVersion")
    public JSONObject getLatestVersion(@RequestParam String type) {
        try {
            return CommonResponse.success(dictService.getLatestVersion(type));
        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SQLERR, "dict query err");
        }
    }
}

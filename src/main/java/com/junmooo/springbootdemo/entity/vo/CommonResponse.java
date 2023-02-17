package com.junmooo.springbootdemo.entity.vo;

import com.alibaba.fastjson.JSONObject;

public class CommonResponse {
    public static JSONObject success(Object data) {
        JSONObject res = new JSONObject();
        res.put("code", 1);
        res.put("msg", "success");
        res.put("data", data);
        return res;
    }
    public static JSONObject fail(int code, String msg) {
        JSONObject res = new JSONObject();
        res.put("code", code);
        res.put("msg", msg);
        return res;
    }
}

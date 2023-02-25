package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.message.Message;
import com.junmooo.springbootdemo.entity.token.OperToken;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.interview.MessageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("addOrUpdate")
    public JSONObject addOrUpdate(@RequestBody Message message, HttpServletRequest request) {
        OperToken operToken = (OperToken) request.getAttribute("operToken");
        try {
            int i;
            if (message.getId() != null) {
                message.setUpdatedTime(System.currentTimeMillis());
                i = messageService.update(message);
            } else {
                message.setAuthorId(operToken.getOperId());
                message.setAuthorName(operToken.getOperName());
                message.setCreatedTime(System.currentTimeMillis());
                i = messageService.add(message);
            }

            if (i >= 1) {
                return CommonResponse.success(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.SYSERR, "添加失败 e");
        }
        return CommonResponse.fail(ErrorCode.SYSERR, "添加失败");
    }

    @PostMapping("query")
    public JSONObject query(@RequestBody Message message, HttpServletRequest request) {
        try {
            OperToken operToken = (OperToken) request.getAttribute("operToken");
            List messages = messageService.messages(message, operToken);
            return CommonResponse.success(messages);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.success("查询失败 e");
        }
    }

    @RequestMapping("delete")
    public JSONObject delete(@Param("id") String id) {
        try {
            int i = messageService.deleteById(id);
            if (i == 1) {
                return CommonResponse.success(null);
            }
            return CommonResponse.fail(ErrorCode.SQLERR, "del fail");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.SQLERR, "del fail");
        }
    }
}

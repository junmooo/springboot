package com.junmooo.springbootdemo.interceptor;

import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.token.UserToken;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.utils.RedisUtils;
import com.junmooo.springbootdemo.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class ClientInterceptor implements HandlerInterceptor {

    private boolean resp403(HttpServletResponse response) throws Exception{
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(403);
        PrintWriter out = response.getWriter();
        out.write(CommonResponse.fail(ErrorCode.WRONGTOKEN, "token 失效").toJSONString());
        out.close();
        response.flushBuffer();
        return false;
    }

    /**
     * handler 对应@RequestMapping对应的controller对象
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String s = "";
        try{
            UserToken userToken = TokenUtils.getInfoFromUserToken(request.getHeader("token"));
            request.setAttribute("userToken",userToken);
        }catch (InvalidJwtException e){
            return resp403(response);
        }
        if (StringUtils.isNotEmpty(token)) {
            s = RedisUtils.get(token);
        }
        if (StringUtils.isEmpty(s)) {
            return resp403(response);
        }
        RedisUtils.set(token, s, 60, TimeUnit.MINUTES);
        return true;//放行
    }
}

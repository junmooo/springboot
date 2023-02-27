package com.junmooo.springbootdemo.interceptor;

import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.token.OperToken;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.utils.RedisUtils;
import com.junmooo.springbootdemo.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class AdminInterceptor implements HandlerInterceptor {

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
            OperToken operToken = TokenUtils.getInfoFromOperToken(request.getHeader("token"));
            request.setAttribute("operToken",operToken);
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

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  {
        //只要对应的preHandle放行了就一定会执行，Controller方法抛异常也不会影响
        System.out.println("afterCompletion");
    }
}

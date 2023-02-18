package com.junmooo.springbootdemo.config;

import com.junmooo.springbootdemo.interceptor.InterceptorDemo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 添加Web项目的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有访问路径，都通过MyInterceptor类型的拦截器进行拦截
        registry.addInterceptor(new InterceptorDemo()).addPathPatterns("/**")
                .excludePathPatterns("/", "*/auth/login","*/interview/*","*/file/*");
        //放行登录页，登陆操作，静态资源
    }
}
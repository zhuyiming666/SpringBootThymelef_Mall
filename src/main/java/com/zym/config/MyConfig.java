package com.zym.config;

import com.zym.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
       /* registry.addViewController("/indexs").setViewName("index");*/
        registry.addViewController("/reg.html").setViewName("reg");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/layerTest.html").setViewName("layerTest");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/shooping_Imgs/upload/**")
               .addResourceLocations("file:D:/学习文件/Template Project/SpringBootThymelef_Mall/src/main/resources/static/shooping_Imgs/upload/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor=new LoginInterceptor();
        List<String> list=new ArrayList<>();
        list.add("/bootstrap3/**");
        list.add("/css/**");
        list.add("/img/**");
        list.add("/js/**");
        list.add("/layer/**");
        list.add("/shooping_Imgs/**");
        list.add("/login.html");
        list.add("/reg.html");
        list.add("/index.html");
        list.add("/index");
        list.add("/proDetail");
        list.add("/users/**");
        list.add("/productByTypeId");
        list.add("/product.html");
        registry.addInterceptor(interceptor).
                addPathPatterns("/**").excludePathPatterns(list);
    }
}

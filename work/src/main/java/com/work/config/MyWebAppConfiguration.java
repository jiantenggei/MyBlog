package com.work.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebAppConfiguration extends WebMvcConfigurationSupport {

    /**
     * @Description:
     * 对文件的路径进行配置, 创建一个虚拟路径/Path/**
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:D:/upload/");
//        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:/root/test/");
        super.addResourceHandlers(registry);
    }



}

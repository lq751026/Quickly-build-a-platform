package com.object.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 图片的静态映射
 */


@Configuration
public class MyPicConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //   /run /images/是对应resource下工程目录
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


}

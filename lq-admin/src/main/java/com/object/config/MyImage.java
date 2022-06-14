package com.object.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 图片的静态映射
 */

@Configuration
public class  MyImage implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + FileConfig.PATH);
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + FileConfig.FILEPATH);
    }
}

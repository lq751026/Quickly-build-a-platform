package com.object.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
public class SwaggerConfig {
    Profiles of = Profiles.of("dev", "test");

    @Bean
    public Docket docket(Environment environment) {
        boolean b = environment.acceptsProfiles(of);
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).enable(b).select()
//                在此包下的类就能扫描
                .apis(RequestHandlerSelectors.basePackage("com.object.module.sys.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("小庆", "http://xiaoqing.work", "1578442339@qq.com");
        return new ApiInfo(
                "小庆",
                "小庆",
                "v1.0",
                "xiaoqing.work",
                contact,
                "许可",
                "xiaoqing.work",
                new ArrayList<>()
        );
    }
}

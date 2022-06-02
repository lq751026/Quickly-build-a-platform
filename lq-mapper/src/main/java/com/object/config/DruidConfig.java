package com.object.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {


    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控功能：步骤都是死的
    //相当于配置web.xml,ServletRegistrationBean
    //因为SpingBoot内置了servlet容器，所有没有web.xml，如果想要配置就使用替代方法：ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后天需要管理员登录，账号密码配置

        //设置初始化参数
     /*   HashMap<String, String> initParameters = new HashMap<>();
        //增加设置        //登录设置的key是固定的
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");

        //设置允许访问权限
        initParameters.put("allow","");

        //设置禁止访问权限
        initParameters.put("qqa","192.1168.11.123");
        initParameters.put("","");
        initParameters.put("","");




        bean.setInitParameters(initParameters);*/
        //bean.setEnabled(true);
        return bean;
    }

    //过滤器
    //配置 Druid 监控 之  web 监控的 filter
    //WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //设置过滤哪些请求,不进行统计监控
        HashMap<String, String> initParameters = new HashMap<>();
        //这些路径下不过滤
        initParameters.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }

}

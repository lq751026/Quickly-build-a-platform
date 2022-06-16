package com.object.config;

import com.object.start.LiCreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 实体类生成表 配置！！
 */
@Component
public class EntityBuilderConfig implements ApplicationRunner {

    @Value("${entity.builder.open}")
    private boolean open;
    @Value("${entity.builder.pack}")
    private String pack;

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /**
         * 判断是否开启实体类生成
         */
        if (open) LiCreateTable.createTable(dataSource, pack);
    }
}

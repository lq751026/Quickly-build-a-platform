package com.object.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表注解
 */
@Target(ElementType.TYPE)//内置注解 使用到我们的类上
@Retention(RetentionPolicy.RUNTIME) //表示在程序运行是也生效
public @interface LiTableName {
    String value();

    String comment() default "";
}

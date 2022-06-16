package com.object.annotation;


import com.object.type.LiFieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段注解
 */
@Target(ElementType.FIELD)//内置注解 使用到我们的类上
@Retention(RetentionPolicy.RUNTIME) //表示在程序运行是也生效
public @interface LiField {

    boolean isPrimaryKey() default false;

    LiFieldType type() ;

    int size() default 100;

    boolean isEmpty() default true;

    String comment() default "";
}

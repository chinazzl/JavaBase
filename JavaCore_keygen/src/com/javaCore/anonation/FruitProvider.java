package com.javaCore.anonation;

import java.lang.annotation.*;

/**
 * 自定义一个注解
 */
@Target(ElementType.FIELD) // 定义在变量中
@Retention(RetentionPolicy.RUNTIME)//运行时使用
@Documented
public @interface FruitProvider {

    // 供应商编号
    public int id() default -1;

    // 供应商名称
    public String name() default "";
}

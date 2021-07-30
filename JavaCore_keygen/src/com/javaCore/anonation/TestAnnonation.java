package com.javaCore.anonation;

import java.lang.reflect.Field;

public class TestAnnonation {
    public static void main(String[] args) {
        String providerInfo = "";
        try {
            Class<?> appLeClazz = Class.forName("com.javaCore.anonation.Apple");
            Field[] declaredFields = appLeClazz.getDeclaredFields();
            for (Field field : declaredFields) {
                if(field.isAnnotationPresent(FruitProvider.class)){
                    FruitProvider fruitProviderAnnon = field.getAnnotation(FruitProvider.class);
                    //注解信息处理
                    providerInfo = "供应商编号：" + fruitProviderAnnon.id() + "  供应商地市：" + fruitProviderAnnon.name();
                    System.out.println(providerInfo);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

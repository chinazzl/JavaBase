package com.javaCore.anonation;

public class Apple {

    @FruitProvider(id = 1,name = "牡丹江")
    private String appleProvider;

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
}

package com.example.demo.domain;

public enum  ProductProperty {
    NAME ("Rest API"),
    PRICE ("Database");

    private final String sourceType;

    ProductProperty(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceType() {
        return sourceType;
    }
}

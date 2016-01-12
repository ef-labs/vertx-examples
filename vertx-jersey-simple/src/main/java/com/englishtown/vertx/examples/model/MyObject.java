package com.englishtown.vertx.examples.model;

/**
 * Sample model object
 */
public class MyObject {

    private String prop1;
    private Integer prop2;
    private Boolean prop3;

    public String getProp1() {
        return prop1;
    }

    public MyObject setProp1(String prop1) {
        this.prop1 = prop1;
        return this;
    }

    public Integer getProp2() {
        return prop2;
    }

    public MyObject setProp2(Integer prop2) {
        this.prop2 = prop2;
        return this;
    }

    public Boolean getProp3() {
        return prop3;
    }

    public MyObject setProp3(Boolean prop3) {
        this.prop3 = prop3;
        return this;
    }
}

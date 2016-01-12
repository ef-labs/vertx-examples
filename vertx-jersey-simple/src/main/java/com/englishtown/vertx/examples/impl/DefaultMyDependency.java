package com.englishtown.vertx.examples.impl;

import com.englishtown.vertx.examples.MyDependency;
import com.englishtown.vertx.examples.model.MyObject;

/**
 * Default implementation
 */
public class DefaultMyDependency implements MyDependency {
    @Override
    public void run(MyObject obj) {
    }
}

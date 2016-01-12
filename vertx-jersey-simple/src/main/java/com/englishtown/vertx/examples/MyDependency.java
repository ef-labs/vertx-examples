package com.englishtown.vertx.examples;

import com.englishtown.vertx.examples.model.MyObject;

/**
 * Dependency to inject
 */
public interface MyDependency {

    void run(MyObject obj);

}

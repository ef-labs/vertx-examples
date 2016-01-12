package com.englishtown.vertx.examples.hk2;

import com.englishtown.vertx.examples.MyDependency;
import com.englishtown.vertx.examples.rest.CorsResponseFilter;
import com.englishtown.vertx.examples.impl.DefaultMyDependency;
import com.englishtown.vertx.hk2.HK2JerseyBinder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * Custom HK2 Binder
 */
public class MyBinder extends AbstractBinder {
    /**
     * Implement to provide binding definitions using the exposed binding
     * methods.
     */
    @Override
    protected void configure() {

        install(new HK2JerseyBinder());

        // My dependencies to inject
        bind(DefaultMyDependency.class).to(MyDependency.class).in(Singleton.class);

        // CORS filter for swagger ui
        bind(CorsResponseFilter.class).to(ContainerResponseFilter.class).in(Singleton.class);
    }
}

package com.englishtown.vertx.examples.rest.resources;

import com.englishtown.vertx.examples.MyDependency;
import com.englishtown.vertx.examples.model.MyObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.vertx.core.Vertx;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Simple JAX-RS resource
 */
@Api("Simple Resource")
@Path("simple")
public class SimpleResource {

    private final MyDependency dependency;

    @Inject
    public SimpleResource(MyDependency dependency) {
        this.dependency = dependency;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get json operation", response = MyObject.class)
    public void getJson(
            @Context Vertx vertx,
            @Suspended AsyncResponse response) {

        vertx.runOnContext(aVoid -> {

            MyObject obj = new MyObject()
                    .setProp1("abc")
                    .setProp2(123)
                    .setProp3(true);

            dependency.run(obj);

            response.resume(obj);

        });

    }

}

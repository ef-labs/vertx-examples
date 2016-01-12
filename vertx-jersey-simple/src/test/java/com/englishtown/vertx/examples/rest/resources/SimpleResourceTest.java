package com.englishtown.vertx.examples.rest.resources;

import com.englishtown.vertx.examples.hk2.MyBinder;
import com.englishtown.vertx.jersey.JerseyVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.test.core.VertxTestBase;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;

/**
 * Integration test for {@link SimpleResource}
 */
public class SimpleResourceTest extends VertxTestBase {

    private String host = "localhost";
    private int port = 8080;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        JsonObject config = new JsonObject()
                .put("hk2_binder", new JsonArray().add(MyBinder.class.getCanonicalName()))
                .put("jersey", new JsonObject()
                        .put("host", host)
                        .put("port", port)
                        .put("resources", new JsonArray().add(SimpleResource.class.getPackage().getName()))
                );

        DeploymentOptions options = new DeploymentOptions()
                .setConfig(config);

        CompletableFuture<String> future = new CompletableFuture<>();

        vertx.deployVerticle("java-hk2:" + JerseyVerticle.class.getCanonicalName(), options, result -> {
            if (result.succeeded()) {
                future.complete(result.result());
            } else {
                future.completeExceptionally(result.cause());
            }
        });

        future.get();
    }

    @Test
    public void testGetJson() throws Exception {

        vertx.createHttpClient()
                .get(port, host, "/simple", response -> {
                    assertEquals(Response.Status.OK.getStatusCode(), response.statusCode());

                    response.bodyHandler(body -> {
                        JsonObject json = new JsonObject(body.toString());
                        assertNotNull(json);
                        testComplete();
                    });
                })
                .setTimeout(5000)
                .end();

        await();
    }

}
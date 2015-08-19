package com.kenzan.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class VertxRunner extends AbstractVerticle {

    public static void main(String[] args) throws Exception {
        Vertx vertx = Vertx.factory.vertx();
        final Router router = Router.router(vertx);

        router.get("/hello").handler(context -> {
            context.response().end("Hello World");
        });

        router.get("/hello/:name").handler(context -> {
            String name = context.request().params().get("name");
            context.response().end("Hello " + name);
        });

        vertx.createHttpServer()
        .requestHandler(router::accept)
        .listen(3000);
    }
}

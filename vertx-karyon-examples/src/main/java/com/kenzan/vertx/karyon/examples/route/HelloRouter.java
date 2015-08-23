package com.kenzan.vertx.karyon.examples.route;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

import com.kenzan.vertx.annotations.VertxHttpMethod;
import com.kenzan.vertx.annotations.VertxPath;
import com.kenzan.vertx.annotations.VertxRouter;

@VertxRouter
public class HelloRouter {

    @VertxPath("/hello")
    public void get(RoutingContext context){
        context.response().end("Hello World");
    }

    @VertxPath("/hello/:name")
    public void getName(RoutingContext context){
        context.response().end("Hello " + context.request().getParam("name"));
    }

    @VertxPath("/hello/:name")
    @VertxHttpMethod(HttpMethod.POST)
    public void postName(RoutingContext context) {

    }

}

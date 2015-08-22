package com.kenzan.vertx.karyon.examples.route;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

import com.kenzan.vertx.annotation.Route;
import com.kenzan.vertx.annotation.VertxRouter;

@VertxRouter
public class HelloRouter {

    @Route("/hello")
    public void get(RoutingContext context){
        context.response().end("Hello World");
    }

    @Route("/hello/:name")
    public void getName(RoutingContext context){
        context.response().end("Hello " + context.request().getParam("name"));
    }

    @Route(value = "/hello/:name", method = HttpMethod.POST)
    public void postName(RoutingContext context) {
        
    }

}

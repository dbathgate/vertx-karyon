package com.kenzan.vertx.config;

import io.vertx.core.Vertx;

import com.google.inject.AbstractModule;

public class VertxModule extends AbstractModule{

    @Override
    protected void configure() {

        Vertx vertx = Vertx.factory.vertx();
        bind(Vertx.class).toInstance(vertx);
    }
}

package com.kenzan.vertx.guice;

import io.vertx.core.Vertx;

import com.google.inject.AbstractModule;



public class VertxHttpModule extends AbstractModule {

    private final Vertx vertx;

    public VertxHttpModule(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    protected void configure() {
        install(new VertxRouteBindingModule(vertx));

        bind(Vertx.class).toInstance(vertx);
        bind(VertxHttpServerStarter.class).asEagerSingleton();
    }

}

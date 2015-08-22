package com.kenzan.vertx.karyon.examples.module;

import com.google.inject.AbstractModule;
import com.kenzan.vertx.karyon.examples.route.HelloRouter;

public class RouterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HelloRouter.class).asEagerSingleton();
    }

}

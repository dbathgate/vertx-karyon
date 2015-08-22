package com.kenzan.vertx;

import io.vertx.core.AbstractVerticle;

import com.netflix.governator.LifecycleInjector;

public abstract class KaryonVerticle extends AbstractVerticle{

    LifecycleInjector injector;

    @Override
    public void start() throws Exception {
        injector = getLifecycleInjector();
    }

    @Override
    public void stop() throws Exception {
        injector.shutdown();
    }

    protected abstract LifecycleInjector getLifecycleInjector();
}

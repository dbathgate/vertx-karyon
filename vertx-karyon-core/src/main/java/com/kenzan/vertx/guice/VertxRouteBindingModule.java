package com.kenzan.vertx.guice;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import com.kenzan.vertx.annotations.VertxRouter;

public class VertxRouteBindingModule extends AbstractModule {

    private final Vertx vertx;

    public VertxRouteBindingModule(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    protected void configure() {

        Router router = Router.router(vertx);

        bindListener(Matchers.any(), new TypeListener() {

            @Override
            public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {

                encounter.register(new InjectionListener<I>() {
                    @Override
                    public void afterInjection(I injectee) {

                        if (injectee.getClass().isAnnotationPresent(VertxRouter.class)) {
                            VertxRouteBinder.bind(injectee, router);
                        }
                    }
                });
            }
        });

        bind(Router.class).toInstance(router);
    }
}

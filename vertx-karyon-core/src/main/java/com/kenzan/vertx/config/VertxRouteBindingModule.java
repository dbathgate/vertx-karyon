package com.kenzan.vertx.config;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;

import java.lang.reflect.Method;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import com.kenzan.vertx.annotation.Route;
import com.kenzan.vertx.annotation.VertxRouter;

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
                            for(Method method : injectee.getClass().getMethods()) {
                                if (method.isAnnotationPresent(Route.class)) {
                                    Route vertxRoute =method.getAnnotation(Route.class);
                                    String path = vertxRoute.value();
                                    HttpMethod httpMethod = vertxRoute.method();

                                    router.route(httpMethod, path).handler(context -> {
                                        try {
                                            method.invoke(injectee, context);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    });
                                }
                            }
                        }
                    }
                });
            }
        });

        bind(Router.class).toInstance(router);
    }
}

package com.kenzan.vertx.guice;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;

@Singleton
public class VertxHttpServerStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(VertxHttpServerStarter.class);

    private static final DynamicIntProperty PORT = DynamicPropertyFactory.getInstance().getIntProperty("vertx.http.port", 8080);

    @Inject
    VertxHttpServerStarter(Vertx vertx, Router router) {
        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(PORT.get());

        LOGGER.info("Vertx http server listening on port " + PORT.get());
    }
}

package com.kenzan.vertx.karyon.examples;

import io.vertx.core.Vertx;

import com.kenzan.vertx.KaryonVerticle;
import com.kenzan.vertx.config.VertxHttpModule;
import com.kenzan.vertx.karyon.examples.module.RouterModule;
import com.netflix.governator.LifecycleInjector;
import com.netflix.karyon.Karyon;
import com.netflix.karyon.archaius.ArchaiusKaryonConfiguration;

public class KaryonExampleRunner extends KaryonVerticle{

    public static void  main(String[] args) throws InterruptedException  {
        Vertx vertx = Vertx.factory.vertx();
        vertx.deployVerticle(KaryonExampleRunner.class.getName());
    }

    @Override
    protected LifecycleInjector getLifecycleInjector() {

        return Karyon.createInjector(
                ArchaiusKaryonConfiguration.builder()
                    .withConfigName("vertx-edge")
                    .build(),
               new VertxHttpModule(vertx),
               new RouterModule()
       );
    }

}

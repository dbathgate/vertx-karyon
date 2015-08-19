package com.kenzan.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import com.englishtown.vertx.guice.GuiceJerseyBinder;
import com.englishtown.vertx.jersey.JerseyOptions;
import com.englishtown.vertx.jersey.JerseyServer;
import com.kenzan.vertx.config.VertxModule;
import com.netflix.governator.LifecycleInjector;
import com.netflix.karyon.Karyon;
import com.netflix.karyon.archaius.ArchaiusKaryonConfiguration;

public class JerseyRunner {

    public static void main(String[] args) throws InterruptedException {
        LifecycleInjector injector = Karyon.createInjector(
            ArchaiusKaryonConfiguration.builder()
                .withConfigName("vertx-edge")
                .build(),
            new VertxModule(),
            new GuiceJerseyBinder()
            );

        JerseyServer jerseyServer = injector.getInstance(JerseyServer.class);
        JerseyOptions jerseyOptions = injector.getInstance(JerseyOptions.class);
        Vertx vertx = injector.getInstance(Vertx.class);

        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        jsonArray.add("com.kenzan.vertx.web");

        jsonObject.put("host", "localhost");
        jsonObject.put("port", 8080);
        jsonObject.put("base_path", "/");
        jsonObject.put("resources", jsonArray);

        jerseyOptions.init(jsonObject, vertx);
        jerseyServer.init(jerseyOptions);

        injector.awaitTermination();
        jerseyServer.close();
    }
}

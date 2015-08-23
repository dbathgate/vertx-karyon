package com.kenzan.vertx.guice;

import java.lang.reflect.Method;

import com.kenzan.vertx.annotations.VertxHttpMethod;
import com.kenzan.vertx.annotations.VertxPath;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;

public class VertxRouteBinder {

    private static final HttpMethod DEFAULT_HTTP_METHOD = HttpMethod.GET;

    public static void bind(Object route, Router router) {

        for(Method method : route.getClass().getMethods()) {

            if (method.isAnnotationPresent(VertxPath.class)) {
                VertxPath vertxPath = method.getAnnotation(VertxPath.class);
                String path = vertxPath.value();
                HttpMethod httpMethod = getHttpMethod(method);

                router.route(httpMethod, path).handler(context -> {
                    try {
                        method.invoke(route, context);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }

    }

    private static HttpMethod getHttpMethod(Method method) {

        if (method.isAnnotationPresent(VertxHttpMethod.class)) {
            VertxHttpMethod vertxHttpMethod = method.getAnnotation(VertxHttpMethod.class);
            return vertxHttpMethod.value();
        }

        return DEFAULT_HTTP_METHOD;
    }
}

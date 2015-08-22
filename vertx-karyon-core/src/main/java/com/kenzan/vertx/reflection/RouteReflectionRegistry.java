package com.kenzan.vertx.reflection;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.kenzan.vertx.annotation.Route;

public class RouteReflectionRegistry {

    public static void bindRoutes(Router router, Object routes) {

        for (Method method : router.getClass().getMethods()) {

            for(Annotation annotation: method.getAnnotations()) {
                if(annotation.annotationType().isAssignableFrom(Route.class)) {
                    String path = Route.class.cast(annotation).value();
                    HttpMethod httpMethod = Route.class.cast(annotation).method();

                    router.route(httpMethod, path).handler(context -> {
                        try {
                            method.invoke(routes, context);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }
    }
}

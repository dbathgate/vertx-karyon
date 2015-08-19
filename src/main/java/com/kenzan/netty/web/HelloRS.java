package com.kenzan.netty.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/hello")
public class HelloRS {

    @GET
    public String doGet() {
        return "Hello World";
    }

    @GET
    @Path("{name}")
    public String getName(@PathParam("name") String name) {
        return "Hello " + name;
    }
}

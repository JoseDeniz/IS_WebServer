package is_server.controller;

import is_server.routes.HelloRoute;

import static spark.Spark.get;

public class HelloController {

    public HelloController() {
        setRoute();
    }

    private void setRoute() {
        get("/hello", new HelloRoute());
    }
}

package is_server.controller;

import is_server.helper.CorsFilter;

import static spark.Spark.get;

public class HelloController {

    public HelloController() {
        setRoute();
    }

    private void setRoute() {
        CorsFilter.apply();
        get("/hello", (request, response) -> "{\"message\": \"Hello World!\"}");
    }
}

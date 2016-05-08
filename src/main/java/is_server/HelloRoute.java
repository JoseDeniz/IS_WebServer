package is_server;

import is_server.helper.CorsFilter;
import spark.Request;
import spark.Response;
import spark.Route;

class HelloRoute implements Route {

    HelloRoute() {
        CorsFilter.apply();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return "{\"message\": \"Hello World!\"}";
    }
}

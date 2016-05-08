package is_server.routes;

import is_server.helper.CorsFilter;
import spark.Request;
import spark.Response;
import spark.Route;

public class HelloRoute implements Route {

    public HelloRoute() {
        CorsFilter.apply();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return "{\"message\": \"Hello World!\"}";
    }
}

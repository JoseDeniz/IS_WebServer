import is_server.JsonTransformer;

import java.util.Optional;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        setConfig();

        get("/hello", new HelloRoute(), new JsonTransformer());

    }

    private static void setConfig() {
        setPort();
        staticFileLocation("/public");
    }

    private static void setPort() {
        String port = System.getenv("PORT");
        port(Optional.ofNullable(port).map(Integer::valueOf).orElse(4567));
    }

}

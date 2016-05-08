package is_server;

import java.util.Optional;

import static spark.Spark.*;

public class Application {

    public Application() {
        setConfig();
    }

    private static void setConfig() {
        setPort();
        staticFileLocation("/public");
    }

    private static void setPort() {
        String port = System.getenv("PORT");
        port(Optional.ofNullable(port).map(Integer::valueOf).orElse(4567));
    }

    private void deploy() {
        get("/hello", new HelloRoute());
    }

    public static void main(String[] args) {
        new Application().deploy();
    }

}

package is_server;

import is_server.controller.HelloController;
import is_server.controller.ProductController;
import is_server.persistence.HashMapRepository;
import is_server.services.ProductService;

import java.util.Optional;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

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

    public void deploy() {
        new HelloController();
        new ProductController(new ProductService(new HashMapRepository()));
    }

    public static void main(String[] args) {
        new Application().deploy();
    }

}

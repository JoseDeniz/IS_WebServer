package is_server.helper;

import spark.Spark;

import java.util.HashMap;
import java.util.Map;

public final class CorsFilter {

    private static final Map<String, String> corsHeaders = new HashMap<>();
    static {
        corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        corsHeaders.put("Access-Control-Allow-Origin", "*");
        corsHeaders.put("Access-Control-Request-Method", "*");
        corsHeaders.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        corsHeaders.put("Access-Control-Allow-Credentials", "true");
    }

    public static void apply() {
        Spark.after((req, res) -> corsHeaders.forEach(res::header));
    }
}
package is_server.test_helper;

import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.fail;

public class TestRequest {

    private static final String LOCALHOST_URL = "http://localhost:4567";

    public static TestResponse doPost(String route) {
        return request("POST", route);
    }

    public static TestResponse doGet(String route) {
        return request("GET", route);
    }

    private static TestResponse request(String method, String route) {
        try {
            HttpURLConnection connection = getConnection(new URL(LOCALHOST_URL + route));
            connection.setRequestMethod(method);
            return getResponse(connection);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static HttpURLConnection getConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    private static TestResponse getResponse(HttpURLConnection connection) throws IOException {
        String body = IOUtils.toString(connection.getInputStream());
        Integer status = connection.getResponseCode();
        return new TestResponse(status, body);
    }
}

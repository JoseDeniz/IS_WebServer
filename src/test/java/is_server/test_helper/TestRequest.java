package is_server.test_helper;

import javaslang.control.Try;
import spark.utils.IOUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestRequest {

    private static final String LOCALHOST_URL = "http://localhost:4567";

    public static TestResponse doPost(String route) {
        return request(connection("POST", route));
    }

    public static TestResponse doGet(String route) {
        return request(connection("GET", route));
    }

    public static TestResponse doPut(String route, String body) {
        return request(connection("PUT", route)
                        .andThen(c -> c.setDoOutput(true))
                        .andThenTry(connection -> attachBody(connection, body)));
    }

    private static TestResponse request(Try<HttpURLConnection> connection) {
        return connection.mapTry(TestRequest::getResponse)
                .onFailure(Throwable::getMessage)
                .get();
    }

    private static Try<HttpURLConnection> connection(String method, String route) {
        return Try.of(() -> (HttpURLConnection) new URL(LOCALHOST_URL + route).openConnection())
                    .andThenTry(urlConnection -> urlConnection.setRequestMethod(method));
    }

    private static void attachBody(HttpURLConnection connection, String body) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(body);
        writer.flush();
        writer.close();
    }

    private static TestResponse getResponse(HttpURLConnection connection) throws IOException {
        String body = IOUtils.toString(connection.getInputStream());
        Integer status = connection.getResponseCode();
        return new TestResponse(status, body);
    }
}

package is_server.controller;

import is_server.Application;
import is_server.test_helper.TestResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import java.io.IOException;
import java.util.Map;

import static is_server.test_helper.TestRequest.doGet;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static spark.Spark.awaitInitialization;

public class HelloControllerTest {

    @BeforeClass
    public static void setUp() throws Exception {
        new Application().deploy();
        awaitInitialization();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Spark.stop();
    }

    @Test public void
    should_get_a_hello_world_message_when_hello_route_is_acceded() throws IOException {
        TestResponse response = doGet("/hello");
        Map<String, Object> map = response.jsonToMap();

        assertThat(response.status(), is(200));
        assertThat(map.get("message"), is("Hello World!"));
    }

}

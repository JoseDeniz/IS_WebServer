package is_server.controller;

import is_server.Application;
import is_server.test_helper.TestResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import java.io.IOException;
import java.util.Map;

import static is_server.test_helper.TestRequest.doPost;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static spark.Spark.awaitInitialization;

public class ProductControllerTest {

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
    should_create_a_product_with_name_foo_and_price_100() throws IOException {
        TestResponse res = doPost("/products?name=foo&price=100");
        Map<String, Object> map = res.jsonToMap();

        assertThat(res.status(), is(200));
        assertThat(map.get("name"), is("foo"));
        assertThat(map.get("price"), is("100"));
        assertThat(map.get("id"), is(notNullValue()));
    }

}

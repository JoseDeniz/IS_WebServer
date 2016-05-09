package is_server.controller;

import is_server.Application;
import is_server.model.Product;
import is_server.test_helper.TestResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spark.Spark;

import java.io.IOException;
import java.util.Map;

import static is_server.test_helper.TestRequest.doGet;
import static is_server.test_helper.TestRequest.doPost;
import static java.util.Collections.emptyMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static spark.Spark.awaitInitialization;

public class ProductControllerTest {

    @Before
    public void setUp() throws Exception {
        new Application().deploy();
        awaitInitialization();
    }

    @After
    public void tearDown() throws Exception {
        Spark.stop();
    }

    @Test
    public void
    should_return_an_empty_collection_when_there_are_no_products() {
        TestResponse response = doGet("/products");
        Map<String, Object> map = response.jsonToMap();

        assertStatusOK(response);
        assertThat(map, is(emptyMap()));
    }

    @Test
    public void
    should_create_a_product_with_name_foo_and_price_100() throws IOException {
        TestResponse response = doPost("/products?name=foo&price=100");
        Map<String, Object> map = response.jsonToMap();

        assertStatusOK(response);
        assertThat(map.get("name"), is("foo"));
        assertThat(map.get("price"), is(100.0));
        assertThat(map.get("id"), is(notNullValue()));
    }

    @Test
    public void
    should_return_a_collection_with_one_element_when_there_is_only_one_product() throws IOException {

        doPost("/products?name=foo&price=100");

        TestResponse response = doGet("/products");
        Map<String, Object> map = response.jsonToMap();
        Product product = new Product(1, "foo", 100.0);

        assertStatusOK(response);
        assertThat(map.values().size(), is(1));
        assertThat(map.values().toArray()[0].toString(), is(product.toString()));
    }

    @Test
    public void
    should_return_a_product_doing_a_get_request_by_its_id() throws IOException {

        doPost("/products?name=foo&price=100");

        TestResponse response = doGet("/products/1");
        Map<String, Object> map = response.jsonToMap();

        assertStatusOK(response);
        assertThat(map.get("name"), is("foo"));
        assertThat(map.get("price"), is(100.0));
        assertThat(map.get("id"), is(notNullValue()));
    }

    private void assertStatusOK(TestResponse response) {
        assertThat(response.status(), is(200));
    }

}

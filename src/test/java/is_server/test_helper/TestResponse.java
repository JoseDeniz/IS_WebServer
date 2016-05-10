package is_server.test_helper;

import is_server.helper.JSONToMap;

import java.util.Map;

public class TestResponse {

    private final Integer status;
    private final String body;

    TestResponse(Integer status, String body) {
        this.status = status;
        this.body = body;
    }u

    public Map<String, Object> jsonToMap() {
        return JSONToMap.fromJson(body);
    }

    public Integer status() {
        return status;
    }

    public String body() {
        return body;
    }
}

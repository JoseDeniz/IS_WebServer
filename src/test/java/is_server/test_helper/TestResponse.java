package is_server.test_helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class TestResponse {

    private final Integer status;
    private final String body;

    TestResponse(Integer status, String body) {
        this.status = status;
        this.body = body;
    }

    public Map<String, Object> jsonToMap() {
        return new Gson().fromJson(body, new TypeToken<Map<String, Object>>(){}.getType());
    }

    public Integer status() {
        return status;
    }
}

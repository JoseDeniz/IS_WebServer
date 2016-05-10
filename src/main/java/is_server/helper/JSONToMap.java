package is_server.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public final class JSONToMap {

    public static Map<String, Object> fromJson(String body) {
        return new Gson().fromJson(body, new TypeToken<Map<String, Object>>(){}.getType());
    }

}

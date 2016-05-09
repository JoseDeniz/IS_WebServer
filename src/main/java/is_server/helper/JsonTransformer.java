package is_server.helper;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public final class JsonTransformer implements ResponseTransformer {

    private final Gson gson;

    public JsonTransformer() {
        gson = new Gson();
    }

    @Override
    public String render(Object data) throws Exception {
        return gson.toJson(data);
    }
}

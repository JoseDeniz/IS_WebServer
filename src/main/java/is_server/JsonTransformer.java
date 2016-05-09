package is_server;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private final Gson gson;

    public JsonTransformer() {
        gson = new Gson();
    }

    @Override
    public String render(Object data) throws Exception {
        return gson.toJson(data);
    }
}

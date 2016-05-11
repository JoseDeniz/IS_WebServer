package is_server.helper;

import is_server.model.Product;

public final class xxxMessageResponse extends Product {

    private final String message;

    public xxxMessageResponse(String message, String id) {
        this.message = String.format(message, id);
    }

    public String getMessage() {
        return this.message;
    }
}

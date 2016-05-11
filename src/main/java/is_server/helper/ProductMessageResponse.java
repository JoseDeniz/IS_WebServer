package is_server.helper;

import is_server.model.Product;

public final class ProductMessageResponse extends Product {

    private final String message;

    public ProductMessageResponse(String message, String id) {
        this.message = String.format(message, id);
    }

    public String getMessage() {
        return this.message;
    }
}

package is_server.helper;

import is_server.model.Product;

public final class ProductResponseError extends Product {

    private final String message;

    public ProductResponseError(String message, Object... args) {
        this.message = String.format(message, args);
    }

    public String getMessage() {
        return this.message;
    }
}

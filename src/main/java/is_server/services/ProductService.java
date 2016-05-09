package is_server.services;

import is_server.helper.ProductResponseError;
import is_server.model.Product;
import spark.Request;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class ProductService {

    private Map<Integer, Product> products;
    private static Integer productId;

    public ProductService() {
        productId = 1;
        products = new HashMap<>();
    }

    public Product createProduct(Request request) {
        Product product = new Product(productId++,
                                      request.queryParams("name"),
                                      parseDouble(request.queryParams("price")));

        return persist(product);
    }

    private Product persist(Product product) {
        if (!products.containsKey(product.getId())) {
            products.put(product.getId(), product);
            return product;
        }
        return new ProductResponseError("The product %d already exists", product.getId());
    }

    public Map<Integer, Product> getAllProducts() {
        return products;
    }
}

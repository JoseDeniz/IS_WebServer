package is_server.services;

import is_server.helper.ProductErrorResponse;
import is_server.model.Product;
import spark.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

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
        return new ProductErrorResponse("The product with id %s already exists", valueOf(product.getId()));
    }

    public Map<Integer, Product> getAllProducts() {
        return products;
    }

    public Product getProductById(String id) {
        return Optional.ofNullable(products.get(parseInt(id)))
                .orElse(new ProductErrorResponse("The product with id %s does not exists", id));
    }
}

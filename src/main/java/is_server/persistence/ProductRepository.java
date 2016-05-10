package is_server.persistence;

import is_server.helper.JSONToMap;
import is_server.helper.ProductErrorResponse;
import is_server.model.Product;
import spark.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class ProductRepository implements Repository<Product> {

    private Map<Integer, Product> products;
    private static Integer productId;

    public ProductRepository() {
        products = new HashMap<>();
        productId = 0;
    }

    @Override
    public Product add(Request request) {
        if (isAnyProductWithSameName(getNameFrom(request))) {
            return productErrorResponse("The product with name %s already exists", getNameFrom(request));
        }
        Product product = product(++productId,
                                  getNameFrom(request),
                                  parsePrice(request.queryParams("price")));
        products.put(productId, product);
        return products.get(productId);
    }

    private String getNameFrom(Request request) {
        return request.queryParams("name");
    }

    private double parsePrice(String price) {
        return parseDouble(price);
    }

    private boolean isAnyProductWithSameName(String name) {
        final Predicate<Product> haveSameNames = product -> product.getName().equals(name);
        return products.values()
                       .stream()
                       .anyMatch(haveSameNames);
    }

    @Override
    public Map<Integer, Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        return Optional.ofNullable(products.get(id))
                .orElse(productErrorResponse("The product with id %s does not exists", valueOf(id)));
    }

    @Override
    public Product update(Request request) {
        Map<String, Object> map = JSONToMap.fromJson(request.body());
        Product product = product(parseInt(request.params("id")),
                                 (String) map.get("name"),
                                 (Double) map.get("price"));
        Integer productId = product.getId();
        if (products.get(productId) == null) {
            return productErrorResponse("The product with id %s does not exists", valueOf(productId));
        }
        products.put(productId, product);
        return products.get(productId);
    }

    private Product product(Integer id, String name, Double price) {
        return new Product(id, name, price);
    }

    private ProductErrorResponse productErrorResponse(String message, String name) {
        return new ProductErrorResponse(message, name);
    }

}

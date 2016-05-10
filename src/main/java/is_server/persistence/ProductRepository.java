package is_server.persistence;

import is_server.helper.JSONToMap;
import is_server.helper.ProductMessageResponse;
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
        if (isAnyProductWithSameName(getNameFromRequest(request))) {
            return productMessageResponse("The product with name %s already exists", getNameFromRequest(request));
        }
        Product product = product(++productId,
                                  getNameFromRequest(request),
                                  parsePrice(request.queryParams("price")));
        products.put(productId, product);
        return products.get(productId);
    }

    private String getNameFromRequest(Request request) {
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
                .orElse(productMessageResponse("The product with id %s does not exists", valueOf(id)));
    }

    @Override
    public Product update(Request request) {
        Integer id = parseInt(request.params("id"));
        if (!productExists(id)) {
            return productMessageResponse("The product with id %s does not exists", valueOf(id));
        }
        Map<String, Object> map = JSONToMap.fromJson(request.body());
        products.put(id, product(id,
                                (String) map.get("name"),
                                (Double) map.get("price")));
        return products.get(id);
    }

    @Override
    public Product delete(Request request) {
        Integer id = parseInt(request.params("id"));
        if (!productExists(id)) {
            return productMessageResponse("The product with id %s does not exists", valueOf(id));
        }
        products.remove(id);
        return productMessageResponse("The product with id %s has been deleted", valueOf(id));
    }

    private boolean productExists(Integer id) {
        return products.get(id) != null;
    }

    private Product product(Integer id, String name, Double price) {
        return new Product(id, name, price);
    }

    private ProductMessageResponse productMessageResponse(String message, String id) {
        return new ProductMessageResponse(message, id);
    }

}

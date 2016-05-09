package is_server.persistence;

import is_server.helper.ProductErrorResponse;
import is_server.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static java.lang.String.valueOf;

public class HashMapRepository implements Repository<Product> {

    private Map<Integer, Product> products;

    public HashMapRepository() {
        products = new HashMap<>();
    }

    @Override
    public Product add(Product product) {
        if (isAnyProductWithSameName(product)) {
            return productErrorResponse("The product with name %s already exists", product.getName());
        }
        products.put(product.getId(), product);
        return product;
    }

    private boolean isAnyProductWithSameName(Product product) {
        final Predicate<Product> haveSameNames = p -> p.getName().equals(product.getName());
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

    private ProductErrorResponse productErrorResponse(String message, String name) {
        return new ProductErrorResponse(message, name);
    }

}

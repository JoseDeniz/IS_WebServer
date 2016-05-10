package is_server.services;

import is_server.helper.JSONToMap;
import is_server.model.Product;
import is_server.persistence.Repository;
import spark.Request;

import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ProductService {

    private final Repository<Product> productRepository;
    private static Integer productId;

    public ProductService(Repository<Product> productRepository) {
        this.productRepository = productRepository;
        productId = 1;
    }

    public Product createProduct(Request request) {
        Product product = new Product(productId++,
                                      getNameFrom(request),
                                      parsePrice(request.queryParams("price")));
        return productRepository.add(product);
    }

    public Map<Integer, Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(parseInt(id));

    public Product updateProduct(Request request) {
        Map<String, Object> map = JSONToMap.fromJson(request.body());
        Product product = new Product(getIdFrom(request),
                                     (String) map.get("name"),
                                     (Double) map.get("price"));
        return productRepository.update(product);
    }

    private Integer getIdFrom(Request request) {
        return parseInt(request.params("id"));
    }

    private String getNameFrom(Request request) {
        return request.queryParams("name");
    }

    private double parsePrice(String price) {
        return parseDouble(price);
    }

}

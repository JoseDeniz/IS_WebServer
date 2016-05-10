package is_server.services;

import is_server.model.Product;
import is_server.persistence.Repository;
import spark.Request;

import java.util.Map;

import static java.lang.Integer.parseInt;

public class ProductService {

    private final Repository<Product> productRepository;

    public ProductService(Repository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Request request) {
        return productRepository.add(request);
    }

    public Map<Integer, Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Request request) {
        return productRepository.findById(parseInt(request.params("id")));
    }

    public Product updateProduct(Request request) {
        return productRepository.update(request);
    }

}

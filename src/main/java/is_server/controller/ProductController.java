package is_server.controller;

import is_server.JsonTransformer;
import is_server.services.ProductService;

import static spark.Spark.post;

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
        setRoutes();
    }

    private void setRoutes() {

        post("/products", (request, response) -> productService.createProduct(request), new JsonTransformer());

    }


}

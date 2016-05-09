package is_server.controller;

import is_server.helper.CorsFilter;
import is_server.helper.JsonTransformer;
import is_server.services.ProductService;

import static spark.Spark.get;
import static spark.Spark.post;

public class ProductController {

    private ProductService productService;
    private JsonTransformer jsonTransformer;

    public ProductController(ProductService productService) {
        this.productService = productService;
        this.jsonTransformer = new JsonTransformer();
        setRoutes();
    }

    private void setRoutes() {
        CorsFilter.apply();
        get("/products", (request, response) -> productService.getAllProducts(), jsonTransformer);
        post("/products", (request, response) -> productService.createProduct(request), jsonTransformer);
        get("/products/:id", (request, response) -> productService.getProductById(request.params("id")), jsonTransformer);

    }


}

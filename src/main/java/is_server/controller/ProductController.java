package is_server.controller;

import is_server.helper.CorsFilter;
import is_server.helper.JsonTransformer;
import is_server.services.ProductService;

import static spark.Spark.*;

public class ProductController {

    private ProductService productService;
    private JsonTransformer jsonTransformer;

    public ProductController(ProductService productService) {
        this.productService = productService;
        this.jsonTransformer = new JsonTransformer();
        setRoutes();
        after((request, response) -> response.type("application/json"));
    }

    private void setRoutes() {
        CorsFilter.apply();
        get("/products", (request, response) -> productService.getAllProducts(), jsonTransformer);
        post("/products", (request, response) -> productService.createProduct(request), jsonTransformer);
        get("/products/:id", (request, response) -> productService.getProductById(request), jsonTransformer);
        put("/products/:id", (request, response) -> productService.updateProduct(request), jsonTransformer);
    }


}

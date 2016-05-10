# IS_WebServer

   RESTService implementation made with [@Azoga](https://github.com/Azoga) in the Systems Engineering subject using [Spark](http://sparkjava.com/)

## Heroku

   The app is deployed in [Heroku](https://www.heroku.com/). You can try it [here](https://is-webserver.herokuapp.com)

## Routes:
    
   * GET("/hello"): returns a Hello World! message
   
   * POST("/products"): creates a new product
   
      * Example of URL: "https://is-webserver.herokuapp.com/products?name=foo&price=100"
   
   * GET("/products"): returns a list of available products
   
   * GET("/products/:id"): returns a specific product
   
   * PUT("/products/:id"): modifies the product attributes
   
      * The header must be Content-Type: application/json
      
      * A example of the body (raw)
      
        ```
        {
          "name": "bar",
          "price": 200
        }
        ```
        
   * DELETE("/products/:id"): deletes a product


## Future improvements:

   * Use postresql as DB
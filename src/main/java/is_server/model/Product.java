package is_server.model;

public class Product {

    private Integer id;
    private String name;
    private String price;

    protected Product() {
    }

    public Product(Integer id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}

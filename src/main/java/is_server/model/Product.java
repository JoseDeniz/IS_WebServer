package is_server.model;

public class Product {

    private Integer id;
    private String name;
    private Double price;

    protected Product() {
    }

    public Product(Integer id, String name, Double price) {
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

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("{" +
                "id=%.1f, " +
                "name=%s, " +
                "price=%.1f" +
                "}",
                id.floatValue(), name, price);
    }
}

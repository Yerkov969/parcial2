import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private String type;
    private double price;

    public Product(String name, String description, String type, double price) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    // Getters...

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}

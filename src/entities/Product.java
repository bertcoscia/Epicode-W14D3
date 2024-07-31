package entities;

import java.util.Random;
import java.util.function.Supplier;

public class Product {
    protected long id;
    protected String name;
    protected String category;
    protected double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
        Supplier<Long> randomIdSupplier = () -> {
            Random random = new Random();
            return random.nextLong(1000, 10000);
        };
        this.id = randomIdSupplier.get();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product " + name + " {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}

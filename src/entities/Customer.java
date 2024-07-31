package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer {
    protected long id;
    protected String name;
    protected int tier;

    public Customer(long id, String name, int tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;
    }

    public Order createOrder(Product... product) {
        List<Product> products = new ArrayList<>(Arrays.asList(product));
        return new Order(this, products);
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

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}

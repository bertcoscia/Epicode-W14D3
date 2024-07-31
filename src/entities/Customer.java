package entities;

import java.util.Random;
import java.util.function.Supplier;

public class Customer {
    protected long id;
    protected String name;
    protected int tier;

    public Customer(String name, int tier) {
        Supplier<Long> randomIdSupplier = () -> {
            Random random = new Random();
            return random.nextLong(1000, 10000);
        };
        this.id = randomIdSupplier.get();
        this.name = name;
        this.tier = tier;
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
        return "Customer " + name + " {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}

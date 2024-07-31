import com.github.javafaker.Faker;
import entities.Customer;
import entities.Order;
import entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            long id = Long.parseLong(faker.idNumber().validSvSeSsn().substring(0, 5));
            String name = faker.lordOfTheRings().character();
            int tier = faker.number().numberBetween(1, 3);
            Customer customer = new Customer(id, name, tier);
            customers.add(customer);
        }
        /*System.out.println("--------CUSTOMERS-------");
        for (Customer customer : customers) System.out.println(customer);*/

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            long id = Long.parseLong(faker.idNumber().validSvSeSsn().substring(0, 5));
            String name = faker.book().title();
            String category = "Books";
            double price = faker.number().randomDouble(2, 10, 250);
            Product product = new Product(id, name, category, price);
            products.add(product);
        }

        for (int i = 0; i < 10; i++) {
            long id = Long.parseLong(faker.idNumber().validSvSeSsn().substring(0, 5));
            String name = faker.pokemon().name();
            String category = "Baby";
            double price = faker.number().randomDouble(2, 10, 250);
            Product product = new Product(id, name, category, price);
            products.add(product);
        }

        for (int i = 0; i < 10; i++) {
            long id = Long.parseLong(faker.idNumber().validSvSeSsn().substring(0, 5));
            String name = faker.beer().name();
            String category = "Boys";
            double price = faker.number().randomDouble(2, 10, 250);
            Product product = new Product(id, name, category, price);
            products.add(product);
        }

        /*System.out.println("--------PRODUCTS-------");
        for (Product product : products) System.out.println(product);*/

        List<Order> orders = new ArrayList<>();
        for (Customer customer : customers) {
            int product1 = faker.number().numberBetween(0, 29);
            int product2 = faker.number().numberBetween(0, 29);
            int product3 = faker.number().numberBetween(0, 29);
            Order order = customer.createOrder(products.get(product1), products.get(product2), products.get(product3));
            orders.add(order);
        }

        System.out.println("--------ORDERS-------");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
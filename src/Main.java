import com.github.javafaker.Faker;
import entities.Customer;
import entities.Order;
import entities.Product;
import functional_interfaces.Discount;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

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
            LocalDate dateOrder = LocalDate.now();
            Order order = customer.createOrder(dateOrder, products.get(product1), products.get(product2), products.get(product3));
            orders.add(order);
        }

       /* System.out.println("--------ORDERS-------");
        for (Order order : orders) {
            System.out.println(order);
        }*/

        /*---------------------------------------------EX1---------------------------------------------*/

        Predicate<Product> isBook = product -> product.getCategory().equals("Books");
        Predicate<Product> costsMoreThanHundred = product -> product.getPrice() > 100;

        List<Product> expensiveBooks = products.stream().filter(isBook.and(costsMoreThanHundred)).toList();

        System.out.println("/*---------------------------------------------EX1---------------------------------------------*/");
        for (Product book : expensiveBooks) {
            System.out.println(book);
        }

        /*---------------------------------------------EX2---------------------------------------------*/

        Predicate<Product> isBaby = product -> product.getCategory().equals("Baby");
        Predicate<Order> containsBaby = order -> order.getProducts().stream().anyMatch(isBaby);

        List<Order> babyOrders = orders.stream()
                .filter(containsBaby)
                .toList();

        System.out.println("/*---------------------------------------------EX2---------------------------------------------*/");
        for (Order order : babyOrders) {
            System.out.println(order);
        }

        /*---------------------------------------------EX3---------------------------------------------*/

        Predicate<Product> isBoys = product -> product.getCategory().equals("Boys");
        Discount minusTenPercent = product -> {
            double price = product.getPrice();
            double discount = (price * 10) / 100;
            product.setPrice(price - discount);
            return product;
        };

        List<Product> boysProducts = products.stream().filter(isBoys).map(minusTenPercent::discountPrice).toList();
        /*List<Product> boysProducts = products.stream().filter(isBoys).map(product -> minusTenPercent.discountPrice(product)).toList();*/

        System.out.println("/*---------------------------------------------EX3---------------------------------------------*/");
        for (Product product : boysProducts) {
            System.out.println(product);
        }

        /*---------------------------------------------EX4---------------------------------------------*/

        for (int i = 0; i < 5; i++) {
            int product = faker.number().numberBetween(0, 29);
            LocalDate firstApril = LocalDate.parse("2024-04-01");
            LocalDate firstJune = LocalDate.parse("2024-06-01");
            Date date = faker.date().between(asDate(firstApril), asDate(firstJune));
            LocalDate dateOrder = asLocalDate(date);
            Order order = customers.get(i).createOrder(dateOrder, products.get(product));
            orders.add(order);
        }

        LocalDate lastDayOfMarch = LocalDate.parse("2024-03-31");
        LocalDate firstJuly = LocalDate.parse("2024-07-01");
        Predicate<Order> isAfterLastDayOfMarch = order -> order.getOrderDate().isAfter(lastDayOfMarch);
        Predicate<Order> isBeforeFirstJuly = order -> order.getOrderDate().isBefore(firstJuly);
        Predicate<Order> isTierTwo = order -> order.getCustomer().getTier() == 2;

        List<Order> twoMonthsOrders = orders.stream()
                .filter(isAfterLastDayOfMarch.and(isBeforeFirstJuly))
                .filter(isTierTwo)
                .toList();

        System.out.println("/*---------------------------------------------EX4---------------------------------------------*/");
        for (Order order : twoMonthsOrders) {
            System.out.println(order);
        }
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
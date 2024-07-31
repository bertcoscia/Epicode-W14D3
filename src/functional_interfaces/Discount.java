package functional_interfaces;

import entities.Product;

public interface Discount {
    Product discountPrice(Product product);
}

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop {

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}

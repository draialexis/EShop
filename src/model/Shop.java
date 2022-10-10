package model;

import data.Stub;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Shop {

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {


        products.add(0, product);
        support.fireIndexedPropertyChange(
                PROP_SHOP,
                0,
                products.size() > 1 ? products.get(1) : null,
                product
        );
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public static final String PROP_SHOP = UUID.randomUUID().toString();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}

package model;

import data.Stub;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Shop implements Serializable {
    public static final String PROP_SHOP_ADD = "model.shop.addProduct";
    public static final String PROP_SHOP_RMV = "model.shop.removeProduct";

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {

        products.add(0, product);
        getSupport().fireIndexedPropertyChange(
                PROP_SHOP_ADD,
                0,
                products.size() > 1 ? products.get(1) : null,
                product
        );
        System.out.println("added " + product.getName() + " ($" + product.getPrice() + ")");
    }

    public void removeProduct(Product product) {

        System.out.println("removed " + product.getName() + " ($" + product.getPrice() + ")");
        int index = products.indexOf(product);
        if (index != -1){
            products.remove(product);
            getSupport().fireIndexedPropertyChange(
                    PROP_SHOP_RMV,
                    index,
                    product,
                    products.get(index)
            );
        }

    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }


    private transient PropertyChangeSupport support;

    private PropertyChangeSupport getSupport() {
        if(support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }

}

package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop implements Serializable {
    public static final String PROP_SHOP_ADD = "model.shop.addProduct";
    public static final String PROP_SHOP_RMV = "model.shop.removeProduct";

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        int index = 0;

        products.add(index, product);
        getSupport().fireIndexedPropertyChange(PROP_SHOP_ADD,
                                               index,
                                               getProducts().size() > index + 1 ? getProducts().get(index + 1) : null,
                                               getProducts().get(index));
    }

    public void removeProduct(Product toRemove) {

        int index = products.indexOf(toRemove);
        if (index > -1) {
            products.remove(index);
            getSupport().fireIndexedPropertyChange(PROP_SHOP_RMV,
                                                   index,
                                                   toRemove,
                                                   getProducts().size() >= index + 1 ? getProducts().get(index) : null);
        }

    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    private transient PropertyChangeSupport support;

    private PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }

}

package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Product implements Serializable {

    private String name;

    private double price;

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public Product() {
        this(null, 0.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldV = getName();
        this.name = name;
        getSupport().firePropertyChange(
                PROP_PRODUCT_NAME,
                oldV,
                getName()
        );
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        Double oldV = getPrice();
        this.price = price;
        getSupport().firePropertyChange(
                PROP_PRODUCT_PRICE,
                oldV,
                getPrice()
        );
    }

    private PropertyChangeSupport support;

    public PropertyChangeSupport getSupport() {
        if(support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public static final String PROP_PRODUCT_NAME = "model.product.name";
    public static final String PROP_PRODUCT_PRICE = "model.product.price";

    public void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }

}

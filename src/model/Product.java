package model;

import org.w3c.dom.css.CSSFontFaceRule;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.UUID;

public class Product {

    private String name;

    private double price;

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldV = getName();
        this.name = name;
        support.firePropertyChange(
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
        support.firePropertyChange(
                PROP_PRODUCT_PRICE,
                oldV,
                getPrice()
        );
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public static final String PROP_PRODUCT_NAME = UUID.randomUUID().toString();
    public static final String PROP_PRODUCT_PRICE = UUID.randomUUID().toString();

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


}

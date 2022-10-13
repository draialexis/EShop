package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static model.Product.PROP_PRODUCT_NAME;
import static model.Product.PROP_PRODUCT_PRICE;

public class ProductVM implements PropertyChangeListener {

    private final Product model;

    public Product getModel() {
        return model;
    }

    private final StringProperty name = new SimpleStringProperty();

    public ProductVM(Object obj) {
        //loads

        if (obj instanceof Product) {
            model = (Product) obj;
        }
        else {
            model = new Product();
        }

        setName(model.getName());
        setPrice(model.getPrice());

        //subscribes
        model.addListener(this);

        // promises to update model
        nameProperty().addListener((__, ___, newV) -> model.setName(newV));
        priceProperty().addListener((__, ___, newV) -> model.setPrice((Double) newV));
    }

    public ProductVM() {
        this(null);
    }

    public ProductVM(String name, Double price) {
        this(new Product(name, price));
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {this.name.set(name);}

    public StringProperty nameProperty() {
        return name;
    }

    private final DoubleProperty price = new SimpleDoubleProperty();

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {this.price.set(price);}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(PROP_PRODUCT_NAME)) {
            setName((String) evt.getNewValue());
        }
        if (evt.getPropertyName().equals(PROP_PRODUCT_PRICE)) {
            setPrice((Double) evt.getNewValue());
        }
    }
}



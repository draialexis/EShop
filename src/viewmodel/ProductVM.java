package viewmodel;

import javafx.beans.property.*;
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

    public ProductVM(String name, Double price) {
        model = new Product();

        //loads / sets
        setName(name == null ? model.getName() : name);
        setPrice(price == null ? model.getPrice() : price);

        //subscribes
        model.addListener(this);

        // promises to update model
        nameProperty().addListener((__, ___, newV) -> model.setName(newV));
        priceProperty().addListener((__, ___, newV) -> model.setPrice((Double) newV));
    }

    public ProductVM(Object o) {
        this(o != null ? ((Product) o).getName() : null,
                o != null ? ((Product) o).getPrice() : null);
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



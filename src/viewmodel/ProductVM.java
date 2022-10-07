package viewmodel;

import javafx.beans.property.*;
import model.Product;

public class ProductVM {

    Product model;
    private final StringProperty name = new SimpleStringProperty();

    public ProductVM(String name, double price) {
        model = new Product();
        model.setPrice(price);
        model.setName(name);
    }

    public String getName() {return name.get();}
    public void setName(String name) {this.name.set(name);}
    public StringProperty nameProperty() {return name;}

    private final DoubleProperty price = new SimpleDoubleProperty();
    public double getPrice() {return price.get();}
    public DoubleProperty priceProperty() {return price;}
    public void setPrice(double price) {this.price.set(price);}


}



package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Garment;

public class GarmentVM extends ProductVM {

    private Garment model;

    public GarmentVM(Object obj) {
        super(obj);
    }

    public GarmentVM() {
        super();
    }

    public GarmentVM(String name, Double price) {
        super(name, price);
    }
}

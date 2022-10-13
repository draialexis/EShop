package viewmodel;

import data.Loadable;
import data.Loader;
import data.Saver;
import data.Stub;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Shop;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ShopVM implements PropertyChangeListener {

    private final Shop model;

    public Shop getModel() {
        return model;
    }

    private final ObservableList<ProductVM> productsVMObs = FXCollections.observableArrayList();
    private final ListProperty<ProductVM> productsVM = new SimpleListProperty<>(productsVMObs);
    public ObservableList<ProductVM> getProductsVM() {return FXCollections.unmodifiableObservableList(productsVM.get());}
    public ReadOnlyListProperty<ProductVM> productsVMProperty() {return productsVM;}

    public ShopVM() {

        Shop tmpModel;
        Loadable loader;
        try {
            loader = new Loader();
            tmpModel = loader.load();
        } catch (IOException | ClassNotFoundException ex1) {
            ex1.printStackTrace();
            try {
                loader = new Stub();
                tmpModel = loader.load();
            } catch (IOException | ClassNotFoundException ex2) {
                ex2.printStackTrace();
                tmpModel = new Shop();
            }
        }

        model = tmpModel;
        //subscribes
        model.addListener(this);

        //loads
        model.getProducts().forEach(p -> productsVMObs.add(new ProductVM(p)));

        //will update with its methods
    }

    public void removeProduct(ProductVM productVM) {
        getModel().removeProduct(productVM.getModel());
    }

    public void addGarmentVM(GarmentVM garmentVM) {
        getModel().addProduct(garmentVM.getModel());
    }

    public void addPerfumeVM(PerfumeVM perfumeVM) {
        getModel().addProduct(perfumeVM.getModel());
    }

    public void save() throws IOException {
        Saver saver = new Saver();
        saver.save(getModel());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Shop.PROP_SHOP_ADD)) {
            productsVMObs.add(((IndexedPropertyChangeEvent) evt).getIndex(), new ProductVM(evt.getNewValue()));
        }
        if (evt.getPropertyName().equals(Shop.PROP_SHOP_RMV)) {
            productsVMObs.remove(((IndexedPropertyChangeEvent) evt).getIndex());
        }
    }
}

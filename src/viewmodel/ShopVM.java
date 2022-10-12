package viewmodel;

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

    private Shop model;

    public Shop getModel() {
        return model;
    }

    private final ObservableList<ProductVM> productsVMObs = FXCollections.observableArrayList();
    private final ListProperty<ProductVM> productsVM = new SimpleListProperty<>(productsVMObs);
    public ObservableList<ProductVM> getProductsVM() {return FXCollections.unmodifiableObservableList(productsVM.get());}
    public ReadOnlyListProperty<ProductVM> productsVMProperty() {return productsVM;}

    public ShopVM() {

        try {
            System.out.println("trying real load");
            model = new Loader().load();
        } catch (IOException | ClassNotFoundException ex1) {
            try {
                System.out.println("trying stub load");
                model = new Stub().load();
            } catch (IOException | ClassNotFoundException ex2) {
                System.out.println("giving up load and creating new");
                model = new Shop();
            }
        }

        //subscribes
        model.addListener(this);

        //loads
        model.getProducts().forEach(p -> productsVMObs.add(new ProductVM(p.getName(), p.getPrice())));

        //will update with its methods
    }

    public void removeProduct(ProductVM productVM) {
        productsVMObs.remove(productVM);
        getModel().removeProduct(productVM.getModel());
        printModel();
    }

    public void addGarmentVM(GarmentVM garmentVM) {
        productsVMObs.add(garmentVM);
        getModel().addProduct(garmentVM.getModel());
        printModel();
    }

    public void addPerfumeVM(PerfumeVM perfumeVM) {
        productsVMObs.add(perfumeVM);
        getModel().addProduct(perfumeVM.getModel());
        printModel();
    }

    public void save() throws IOException {
        Saver saver = new Saver();
        saver.save(getModel());
    }

    private void printModel() {
        System.out.println("printing model.................");
        getModel().getProducts().forEach(p -> System.out.println(p.getName() + " : " + p.getPrice()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Shop.PROP_SHOP_ADD)) {
            ProductVM pvm = new ProductVM(evt.getNewValue());
            productsVMObs.add(((IndexedPropertyChangeEvent) evt).getIndex(), pvm);
        }
        if (evt.getPropertyName().equals(Shop.PROP_SHOP_RMV)) {
            productsVMObs.remove(new ProductVM(evt.getOldValue())); // redefined equals an toHash
        }
    }
}

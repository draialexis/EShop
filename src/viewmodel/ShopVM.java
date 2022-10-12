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

    private final Shop model;

    public Shop getModel() {
        return model;
    }

    private final ObservableList<ProductVM> productsVMObs = FXCollections.observableArrayList();
    private final ListProperty<ProductVM> productsVM = new SimpleListProperty<>(productsVMObs);
    public ObservableList<ProductVM> getProductsVM() { return FXCollections.unmodifiableObservableList(productsVM.get()); }
    public ReadOnlyListProperty<ProductVM> productsVMProperty() { return productsVM; }

    public ShopVM() {
        Shop tmpModel;

        try {
            System.out.println("trying real load");
            tmpModel = new Loader().load();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("trying stub load");
                tmpModel = new Stub().load();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("giving up load and creating new");
                tmpModel= new Shop();
            }
        }

        //loads
        model = tmpModel;
        model.getProducts().forEach(p -> productsVMObs.add(new ProductVM(p.getName(), p.getPrice())));

        //subscribes
        model.addListener(this);

        //will update with its methods
    }

    public void removeProduct(ProductVM productVM) {
        productsVMObs.remove(productVM);
        getModel().removeProduct(productVM.getModel());
    }

    public void addGarmentVM(GarmentVM garmentVM) {
        productsVMObs.add(garmentVM);
        getModel().addProduct(garmentVM.getModel());
    }

    public void addPerfumeVM(PerfumeVM perfumeVM) {
        productsVMObs.add(perfumeVM);
        getModel().addProduct(perfumeVM.getModel());
    }

    public void save() throws IOException {
        Saver saver = new Saver();
        saver.save(getModel());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Shop.PROP_SHOP_ADD)) {
            ProductVM pvm = new ProductVM(evt.getNewValue());
            productsVMObs.add(
                    ((IndexedPropertyChangeEvent) evt).getIndex(),
                    pvm
            );
        }
        if (evt.getPropertyName().equals(Shop.PROP_SHOP_RMV)) {
            productsVMObs.remove(new ProductVM(evt.getOldValue())); // redefined equals an toHash
        }
    }
}

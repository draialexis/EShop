package viewmodel;

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

public class ShopVM implements PropertyChangeListener {

    private final ObservableList<ProductVM> productsVMObs = FXCollections.observableArrayList();
    private final ListProperty<ProductVM> productsVM = new SimpleListProperty<>(productsVMObs);

    public ObservableList<ProductVM> getProductsVM() {
        return FXCollections.unmodifiableObservableList(productsVM.get());
    }

    public ReadOnlyListProperty<ProductVM> productsVMProperty() {
        return productsVM;
    }

    public ShopVM() {
        Shop model = new Stub().load();
        model.getProducts().forEach(p -> productsVMObs.add(new ProductVM(p.getName(), p.getPrice())));
    }

    public void removeProduct(ProductVM productVM) {
        productsVMObs.remove(productVM);
    }

    public void addGarmentVM(GarmentVM garmentVM) {

    }

    public void addPerfumeVM(PerfumeVM perfumeVM) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Shop.PROP_SHOP)) {
            ProductVM pvm = ((ProductVM) evt.getNewValue());
            productsVMObs.add(
                    ((IndexedPropertyChangeEvent) evt).getIndex()
                    , new ProductVM(pvm.getName(), pvm.getPrice())
            );
        }
    }
}

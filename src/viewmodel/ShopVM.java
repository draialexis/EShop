package viewmodel;

import data.Stub;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Perfume;
import model.Shop;

public class ShopVM {

    private Shop model;

    private final ObservableList<ProductVM> productsVMObs = FXCollections.observableArrayList();
    private final ListProperty<ProductVM> productsVM = new SimpleListProperty<>(productsVMObs);
    public ObservableList<ProductVM> getProductsVM() {return FXCollections.unmodifiableObservableList(productsVM.get());}
    public ReadOnlyListProperty<ProductVM> productsVMProperty() {return productsVM;}

    public ShopVM() {
//        model = new Shop();
        model = new Stub().load();
        model.getProducts().forEach(p -> {
            ProductVM productVM = new ProductVM(p.getName(), p.getPrice());
            if(p instanceof Perfume) {
//                PerfumeVM perfumeVM = (PerfumeVM) productVM;
//                ... foreach... perfumeVM.addSmell
            }
//            productsVMObs.add
        });
    }

    public void removeProduct(ProductVM productVM) {
        productsVMObs.remove(productVM);
    }

    public void addGarmentVM(GarmentVM garmentVM) {

    }

    public void addPerfumeVM(PerfumeVM perfumeVM) {
    }
}

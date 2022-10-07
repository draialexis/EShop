package view;

import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import viewmodel.GarmentVM;
import viewmodel.PerfumeVM;
import viewmodel.ProductVM;
import viewmodel.ShopVM;

import java.util.List;

public class MainWindow {
    @FXML
    private HBox detailHBox;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField priceTF;
    @FXML
    private ListView<ProductVM> productsVMLV;

    private ShopVM shopVM;

    @FXML
    private void addGarment() {
        GarmentVM garmentVM = null;
        // open a new window, get the input
        shopVM.addGarmentVM(garmentVM);
    }

    @FXML
    private void addPerfume() {
        PerfumeVM perfumeVM = null;
        // open a new window, get the input
        shopVM.addPerfumeVM(perfumeVM);    }

    @FXML
    private void removeProduct() {
        shopVM.removeProduct(productsVMLV.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void initialize() {
        shopVM = new ShopVM();

        productsVMLV.itemsProperty().bind(shopVM.productsVMProperty());

        addListenerProductsVMLV();

        setCellFactoryProductsVMLV();

    }

    private void addListenerProductsVMLV() {
        productsVMLV.getSelectionModel().selectedItemProperty().addListener((__, oldV, newV) -> {
            if (oldV != null) {
                nameTF.textProperty().unbindBidirectional(oldV.nameProperty());
                priceTF.textProperty().unbindBidirectional(oldV.priceProperty());
            }
            if (newV != null) {
                nameTF.textProperty().bindBidirectional(newV.nameProperty());
                priceTF.textProperty().bindBidirectional(newV.priceProperty(), new NumberStringConverter());
            }
        });
    }

    private void setCellFactoryProductsVMLV() {
        productsVMLV.setCellFactory(__ -> new ListCell<>() {
            @Override
            protected void updateItem(ProductVM item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    textProperty().bind(item.nameProperty());
                } else {
                    textProperty().unbind();
                    setText("");
                }
            }
        });
    }
}

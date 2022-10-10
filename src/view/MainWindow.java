package view;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import viewmodel.GarmentVM;
import viewmodel.PerfumeVM;
import viewmodel.ProductVM;
import viewmodel.ShopVM;

import java.io.IOException;

public class MainWindow {

    private final static int GARMENT = 0;
    private final static int PERFUME = 1;
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
    private void clickAddGarment() {
        prepareCreationWindow(GARMENT);
    }

    @FXML
    private void clickAddPerfume() {
        prepareCreationWindow(PERFUME);
    }


    private void prepareCreationWindow(int code) {
        Stage creationWindowStage = new Stage();
        creationWindowStage.initOwner(productsVMLV.getScene().getWindow());
        creationWindowStage.initModality(Modality.WINDOW_MODAL);

        ProductCreationWindow controller = initProductCreationWindow(creationWindowStage);

        String name = controller.getProductVMName();
        String priceString = controller.getProductVMPrice();

        try {
            if (name != null && priceString != null) {
                addProductVMToShop(name, priceString, code);
            }
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR,
                    "could not parse PRICE as a number, please try again",
                    ButtonType.OK)
                    .setHeaderText("yikes");
        }
    }

    private void addProductVMToShop(String name, String priceString, int code) {

        if (code == GARMENT) {
            shopVM.addGarmentVM(new GarmentVM(name, Double.valueOf(priceString)));
        }
        if (code == PERFUME) {
            shopVM.addPerfumeVM(new PerfumeVM(name, Double.valueOf(priceString)));

        }
    }

    private ProductCreationWindow initProductCreationWindow(Stage creationWindowStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductCreationWindow.fxml"));
        ProductCreationWindow controller = new ProductCreationWindow();

        loader.setController(controller);

        try {
            creationWindowStage.setScene(new Scene(loader.load()));
            creationWindowStage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,
                    "error while opening product creation window",
                    ButtonType.OK)
                    .setHeaderText("woopsie");
        }

        return controller;
    }


    @FXML
    private void clickRemoveProduct() {
        shopVM.removeProduct(productsVMLV.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void initialize() {
        shopVM = new ShopVM();
//        shopVM.getProductsVM().forEach(System.out::println);
        productsVMLV.itemsProperty().bind(shopVM.productsVMProperty());
        addListenerProductsVMLV();
        productsVMLV.setCellFactory(__ -> new ProductCell());
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
}

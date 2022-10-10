package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ProductCreationWindow {

    private String productVMName = null;
    private String productVMPrice = null;

    @FXML
    private HBox formHBox;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField priceTF;

    @FXML
    private void clickCancel() {
        productVMName = null;
        productVMPrice = null;
        close();
    }

    @FXML
    private void clickConfirm() {
        productVMName = nameTF.getText();
        productVMPrice = priceTF.getText();
        close();
    }

    private void close() {
        nameTF.getScene().getWindow().hide();
    }

    public String getProductVMName() {
        return productVMName;
    }

    public String getProductVMPrice() {
        return productVMPrice;
    }
}

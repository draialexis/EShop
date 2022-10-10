package view;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;
import viewmodel.ProductVM;

public class ProductCell extends ListCell<ProductVM> {

    private final Label nameLbl = new Label();
    private final Label priceLbl = new Label();
    private final BorderPane pane = new BorderPane();

    @Override
    protected void updateItem(ProductVM item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            nameLbl.textProperty().bind(item.nameProperty());
            priceLbl.textProperty().bind(item.priceProperty().asString());
            pane.setLeft(nameLbl);
            pane.setRight(priceLbl);
            setGraphic(pane);
        } else {
            setGraphic(null);
        }
    }
}

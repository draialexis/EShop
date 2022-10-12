package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import view.MainWindow;
import viewmodel.ShopVM;

import java.io.IOException;
import java.util.Objects;

public class Launcher extends Application {

    private final ShopVM viewmodel = new ShopVM();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));

        loader.setController(new MainWindow(viewmodel));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        try {
            viewmodel.save();
        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, "was not able to save", ButtonType.OK).setHeaderText(null);
        }
        super.stop();
    }
}

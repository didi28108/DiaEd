package application.view;

import application.Store;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by ucfan on 2017/3/27.
 */
public class Canvas extends AnchorPane {
    private Store store;

    public Canvas(Store store) {
        this.store = store;

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/Canvas.fxml")
        );

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
        // 點擊到畫布時，取消選取狀態
        this.setOnMouseClicked(event -> {
            store.setSelected(null);
        });
    }

    public void clear() {
        this.getChildren().clear();
    }

}

package diaed;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();

		try {
			Scene scene = new Scene(root,840,680);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}

		// 先載入 Mediator
		Store store = new Store();

        root.setCenter(store.getCanvas());
		root.setTop(store.getToolbar());
	}

    public static void main(String[] args) {
        launch(args);
    }


}

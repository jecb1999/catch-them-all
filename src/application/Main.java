package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	private AnchorPane root;
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MenuWindow.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("MENU");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimary() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

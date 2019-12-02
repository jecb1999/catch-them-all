package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Game;
import model.Sphere;
import threads.MovementThread;

public class ControllerMenu implements Initializable {

	private Color[] colors;
	private static Game game;
	private static Main main;
	private static Stage stageGame;
	private MovementThread th;
	private ArrayList<Circle> lc;
	private Pane game2;
	private ArrayList<Sphere> spheres;

	@FXML
	private TextField name;
	@FXML
	private Button searchArchive;
	@FXML
	private Button level1;
	@FXML
	private Button level2;
	@FXML
	private Button level3;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		game = new Game();
		lc = new ArrayList<Circle>();
		colors = new Color[8];
		colors[0] = Color.DARKGOLDENROD;
		colors[1] = Color.BLUE;
		colors[2] = Color.GOLD;
		colors[3] = Color.SILVER;
		colors[4] = Color.CHARTREUSE;
		colors[5] = Color.AQUA;
		colors[6] = Color.RED;
		colors[7] = Color.DARKMAGENTA;
		spheres = game.getSpheres();
		main = new Main();
		stageGame = new Stage();
		game2 = new AnchorPane();
		th = new MovementThread(this);
	}

	public Stage getStage() {
		return stageGame;
	}
	
	public Game getGame() {
		return game;
	}
	
	public Main getMain() {
		return main;
	}

	public void clickLevel1(ActionEvent ae) {
		try {
			start(".\\level1.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickLevel2(ActionEvent ae) {
		try {
			start(".\\level2.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void seeHOF(ActionEvent ae) throws Exception {
		hallOfFame();
	}

	public void clickLevel3(ActionEvent ae) {
		try {
			start(".\\level3.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickLoad(ActionEvent ae) throws Exception {
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Load Game");
		 File file = fileChooser.showOpenDialog(null);
		 if(file != null) {
			 String path = file.getAbsolutePath();
			 start(path);
		 }
	}

	public void clickCircle(MouseEvent me, int i) {
		spheres.get(i).setClick(true);
		if (game.gameOver() == true) {
			try {
				end();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void start(String path) throws Exception {
		game.readLevel(path);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("GameWindow.fxml"));
		game2 = loader.load();
		stageGame.setTitle("JUEGO");
		for (int i = 0; i < spheres.size(); i++) {
			Circle circle = new Circle(spheres.get(i).getPosX(), spheres.get(i).getPosY(), spheres.get(i).getRadium());
			circle.setId(Integer.toString(i));
			circle.setOnMouseClicked(e -> {
				clickCircle(e, Integer.parseInt(circle.getId()));
			});
			circle.setFill(colors[i]);
			lc.add(circle);
			game2.getChildren().add(lc.get(i));
		}
		Scene scene = new Scene(game2);
		main.getPrimary().close();
		th.start();
		stageGame.setScene(scene);
		stageGame.show();
	}
	
	public void movement() throws Exception {
		for (int i = 0; i < lc.size(); i++) {
			if (spheres.get(i).getClick() == false) {
				if (spheres.get(i).getDirections() == 'A') {
					if(lc.get(i) != null) {
					lc.get(i).setCenterY(lc.get(i).getCenterY() - 1);
					spheres.get(i).setPosY(lc.get(i).getCenterY());
					}
					if (lc.get(i).getCenterY() < lc.get(i).getRadius()+28) {
						spheres.get(i).setDirections('B');
						game.setBouncing(game.getBouncing() + 1);
					}
				}
				if (spheres.get(i).getDirections() == 'B') {
					if(lc.get(i) != null) {
					lc.get(i).setCenterY(lc.get(i).getCenterY() + 1);
					spheres.get(i).setPosY(lc.get(i).getCenterY());
					}
					if (lc.get(i).getCenterY() + lc.get(i).getRadius() + 1 > game2.getPrefHeight()) {
						spheres.get(i).setDirections('A');
						game.setBouncing(game.getBouncing() + 1);
					}
				}
				if (spheres.get(i).getDirections() == 'D') {
					if(lc.get(i) != null) {
					lc.get(i).setCenterX(lc.get(i).getCenterX() + 1);
					spheres.get(i).setPosX(lc.get(i).getCenterX());
					}
					if (lc.get(i).getCenterX() + lc.get(i).getRadius() + 1 > game2.getPrefWidth()) {
						spheres.get(i).setDirections('I');
						game.setBouncing(game.getBouncing() + 1);
					}
				}
				if (spheres.get(i).getDirections() == 'I') {
					if(lc.get(i) != null) {
					lc.get(i).setCenterX(lc.get(i).getCenterX() - 1);
					spheres.get(i).setPosX(lc.get(i).getCenterX());
					}
					if (lc.get(i).getCenterX() < lc.get(i).getRadius()) {
						spheres.get(i).setDirections('D');
						game.setBouncing(game.getBouncing() + 1);
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void end() throws Exception {
		if (game.gameOver() == true) {
			th.stop();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("EndWindow.fxml"));
			AnchorPane end = loader.load();
			Scene scene = new Scene(end);
			game.addScore(game.finalScore(), name.getText());
			game.serializableList();
			stageGame.setScene(scene);
			stageGame.show();
		}
	}

	public void hallOfFame() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("HallOfFame.fxml"));
		AnchorPane hall = loader.load();
		Scene scene = new Scene(hall);
		stageGame.setScene(scene);
		stageGame.show();

	}
}

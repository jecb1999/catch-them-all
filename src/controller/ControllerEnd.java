package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class ControllerEnd implements Initializable {

	private static ControllerMenu cm;
	
	@FXML
	private Label score;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cm = new ControllerMenu();
		score();
	}
	
	public void hallOfFame(ActionEvent ae) throws Exception {
		cm.hallOfFame();
	}
	

	public void score() {
		score.setText(Integer.toString(cm.getGame().finalScore()));
	}

}

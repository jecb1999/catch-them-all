package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;

public class ControllerGame implements Initializable {

	private static ControllerMenu cm;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cm = new ControllerMenu();
	}

	public void clickHof(ActionEvent ae) throws Exception {
		cm.hallOfFame();
	}

	public void save(ActionEvent ae) {
		cm.getGame().saveGame();
	}

}

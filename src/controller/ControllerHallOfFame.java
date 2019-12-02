package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ControllerHallOfFame implements Initializable{
	
	private static ControllerMenu cm;
	@FXML
	private Label table;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cm = new ControllerMenu();
		table();
	}
	
	public void backToMenu() {
		cm.getStage().close();
		cm.getMain().start(cm.getMain().getPrimary());
	}
	
	public void table() {
		String[] table1 = cm.getGame().printHallOfFame();
		String text = "";
		for(int i = 0;i<table1.length;i++) {
			if(table1[i] != null) {
				text += Integer.toString(i+1)+")" + table1[i]+"\n"; 
			}
		}
		table.setText(text);
	}

}

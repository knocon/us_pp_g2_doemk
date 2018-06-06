package klassen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerRegistrierung {
	
	@FXML
	private TextField nameFeld;
	@FXML
	private TextField pwFeld;
	@FXML
	private ComboBox<String> comboRolle;
	
	@FXML
	private Button loginButton;
	@FXML
	void loginGeklickt(ActionEvent event) {
		String name = nameFeld.getText();
		String pw = pwFeld.getText();
		String rolle = comboRolle.getValue();
		
		if(name.isEmpty() || pw.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			
			// Account anlegen
			
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	}
	
	public void initialize() {
	}
}

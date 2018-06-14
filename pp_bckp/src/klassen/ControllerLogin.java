package klassen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerLogin {
	Controller controller;
	
	@FXML
	private TextField nameFeld;
	@FXML
	private PasswordField pwFeld;
	
	@FXML
	private Button loginButton;
	@FXML
	void loginGeklickt(ActionEvent event) {
		String name = nameFeld.getText();
		String pw = pwFeld.getText();
		
		if(name.isEmpty() || pw.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			AccountLogik accLogik = new AccountLogik();
			Account acc = accLogik.login(name, pw);
			if(acc!=null) {
				controller.login(acc);
				((Node)(event.getSource())).getScene().getWindow().hide();
			}
			else {
				Alert abfrage = new Alert(AlertType.ERROR,"Falsche Daten", ButtonType.OK);
				abfrage.show();
			}
		}
	}
	
	public void setzeController(Controller controller) {
		this.controller = controller;
	}
	
	public void initialize() {
	}
}

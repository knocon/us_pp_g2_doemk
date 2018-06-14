package klassen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private TextField idFeld;
	@FXML
	private ComboBox<String> comboRolle;
	
	@FXML
	private Button regButton;
	@FXML
	void regGeklickt(ActionEvent event) {
		String name = nameFeld.getText();
		String pw = pwFeld.getText();
		String rolle = comboRolle.getValue();
		try {
			int id = Integer.parseInt(idFeld.getText());
			int accountLevel = Controller.getEingeloggterAccount();
			if(name.isEmpty() || pw.isEmpty()){
				Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
				alert.showAndWait();
			}
			else if(accountLevel!=2 && rolle.equals("Verwalter")) {
				Alert alert = new Alert(AlertType.ERROR,"Sie müssen als Verwalter eingeloggt sein.", ButtonType.OK);
				alert.show();
			}
			else {
				AccountLogik accLogik = new AccountLogik();
				accLogik.addAccount(name, pw, rolle, id);
				
				((Node)(event.getSource())).getScene().getWindow().hide();
			}
		}
		catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR,"Im Feld ID muss eine Zahl angegeben werden.", ButtonType.OK);
			alert.showAndWait();
		}
	}
	
	public void initialize() {
		ObservableList<String> rollen = FXCollections.observableArrayList("Benutzer", "Verwalter");
		comboRolle.setItems(rollen);
		comboRolle.getSelectionModel().selectFirst();
	}
}

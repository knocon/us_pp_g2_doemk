package klassen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerTopf {

	
	static ObservableList options = FXCollections.observableArrayList();

	@FXML
	private TextField nameFeld;
	@FXML
	private TextField soll;
	@FXML
	private TextField ist;
	

	
	@FXML
	private Button speichernButton;
	@FXML
	void speichern(ActionEvent event) {
		String name = nameFeld.getText();
		String sollBestand = soll.getText();
		String istBestand = ist.getText();
		Topf t = new Topf(1,name, sollBestand, istBestand);
		if(name.isEmpty()||sollBestand.isEmpty()||istBestand.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			
			Verwaltung.addTopf(t);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	}
	

}

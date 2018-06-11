package klassen;

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

public class ControllerKategorien {
	static ObservableList options = FXCollections.observableArrayList();
	@FXML
	private TextField nameFeld;
	
	@FXML
	private Button speichernButton;
	@FXML
	void speichern(ActionEvent event) {
		String name = nameFeld.getText();
		Kategorie k = new Kategorie(name);
		
		if(name.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			
			Verwaltung.addKategorie(k);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	}
	
	public void ladeNamen(String kategorie) {
		nameFeld.setText(kategorie);
	}
	
	public void initialize() {
	}
}

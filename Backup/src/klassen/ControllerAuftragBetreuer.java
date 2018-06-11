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

public class ControllerAuftragBetreuer {
	//
	
	@FXML
	private TextField feld1;
	@FXML
	private TextField feld2;
	@FXML
	private TextField feld3;
	@FXML
	private TextField feld4;
	@FXML
	private ComboBox<String> combo1;
	@FXML
	private ComboBox<String> combo2;
	@FXML
	private ComboBox<String> combo3;
	@FXML
	private ComboBox<String> combo4;
	
	@FXML
	private Button speichernButtonBetreuer;
	@FXML
	void betreuerSpeichern(ActionEvent event) {
		
	}
	
	public void initialize() {
	}
	
	public void setzePerson(Person p) {
		
		// Hier wäre wahrscheinlich direkte Anfrage an Verwaltung das beste oder ArrayList Personen Übergabe
		
	}
}

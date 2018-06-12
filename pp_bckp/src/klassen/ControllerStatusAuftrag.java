package klassen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerStatusAuftrag {
	
	@FXML
	private Label datum1;
	@FXML
	private Label datum2;
	@FXML
	private Label datum3;
	@FXML
	private Label datum4;
	@FXML
	private Label datum5;
	@FXML
	private Label datum6;
	@FXML
	private Label datum7;
	
	
	@FXML
	private Button schliessenButtonAuf;
	@FXML
	void schliessenAuf(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public void setStatus(Rechnung[] rechnung) {
		
		//  Hier müssen die Daten an die Labels übergeben werden
		
	}
	
	public void initialize() {
	}
}

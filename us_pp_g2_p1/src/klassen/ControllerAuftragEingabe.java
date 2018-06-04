package klassen;

import com.sun.glass.ui.Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public abstract class ControllerAuftragEingabe extends Application {
	@FXML
	private TextField titelFeld;
	@FXML
	private TextField dateiFeld;
	@FXML
	private TextField pKostenFeld;
	@FXML
	private TextField reeleKFeld;
	@FXML
	private ComboBox comboAuftragEin;
	
	@FXML
	private Button speichernButtonAuftrag;
	@FXML
	void speichernButtonAuftrag(ActionEvent event) {
		String titel = titelFeld.getText();
		String datei = dateiFeld.getText();
		String pKosten = pKostenFeld.getText();
		String rKosten = reeleKFeld.getText();
		String art = comboAuftragEin.getValue();
		
		if(titel.isEmpty() || datei.isEmpty()
				|| pKosten.isEmpty() || rKosten.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			long time = System.currentTimeMillis();
			Auftrag a = new Auftrag(0, titel, rKosten, rKosten, time, rKosten, null)
			verwaltung.addPerson(p);
			((Node)(event.getSource())).getScene().getWindow().hide();
			ladeAllePersonen();
			System.out.println("Person angelegt");
		}
	}
	
	@Override 
	public void initialize() {
		
	}
	
}

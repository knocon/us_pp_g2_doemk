
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

public class ControllerAuftragEingabe {
	
	@FXML
	private TextField titelFeld;
	@FXML
	private TextField dateiFeld;
	@FXML
	private TextField pKostenFeld;
	@FXML
	private TextField reeleKFeld;
	@FXML
	private ComboBox<String> comboAuftragEin;
	
	String titel;
	
	@FXML
	private Button speichernButtonAuftrag;
	@FXML
	void auftragSpeichern(ActionEvent event) {
		String titel = titelFeld.getText();
		String datei = dateiFeld.getText();
		String pKosten = pKostenFeld.getText();
		double Pkosten = Double.parseDouble(pKosten);
		String rKosten = reeleKFeld.getText();
		double Rkosten = Double.parseDouble(rKosten);
		String art = comboAuftragEin.getValue();
		
		if(titel.isEmpty() || datei.isEmpty()
				|| pKosten.isEmpty() || rKosten.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			long time = System.currentTimeMillis();
			Auftrag a = new Auftrag(1, titel, art, datei, Rkosten, Pkosten, time,null);
			Verwaltung verwaltung = new Verwaltung();
			verwaltung.addAuftrag(a);
			((Node)(event.getSource())).getScene().getWindow().hide();
			System.out.println("Auftrag angelegt");
		}
	}
	
	public void initialize() {
		
	}
	
	public void setzeAuftrag(Auftrag a) {
		titelFeld.setText(a.getTitel());
		dateiFeld.setText(a.getDateiname());
		double pKosten = a.getPkosten();
		String Pkosten = String.valueOf(pKosten);
		pKostenFeld.setText(Pkosten);
		double rKosten = a.getRkosten();
		String Rkosten = String.valueOf(rKosten);
		reeleKFeld.setText(Rkosten);
	}
	
}
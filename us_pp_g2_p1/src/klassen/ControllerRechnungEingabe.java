package klassen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerRechnungEingabe {
	
	
	@FXML
	private TextField nameFeld;
	@FXML
	private TextField auftraggeberFeld;
	@FXML
	private TextField kasseFeld;
	@FXML
	private TextField artFeld;
	@FXML
	private TextField datumFeld;
	@FXML
	private TextField ansprechpartnerFeld;
	@FXML
	private TextField topfFeld;
	@FXML
	private TextField kontoFeld;
	
	@FXML
	private Button speichernButtonRechnung;
	@FXML
	void rechnungpeichern(ActionEvent event) {
		String name = nameFeld.getText();
		String auftraggeber = auftraggeberFeld.getText();
		String kasse = kasseFeld.getText();
		String art = artFeld.getText();
		String datum = datumFeld.getText();
		String ansprechpartner = ansprechpartnerFeld.getText();
		String topf = topfFeld.getText();
		String konto = kontoFeld.getText();
		
		if(name.isEmpty() || auftraggeber.isEmpty()
				|| kasse.isEmpty() || art.isEmpty()
				|| datum.isEmpty() || ansprechpartner.isEmpty() 
				|| topf.isEmpty() || konto.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			long time = System.currentTimeMillis();
			//Person r = new Rechnung(1, time ,vorname, nachname, rolle, strasse, nummer, stadt, telefon, email);
			Verwaltung verwaltung = new Verwaltung();
			//verwaltung.addPerson(r);
			((Node)(event.getSource())).getScene().getWindow().hide();
			System.out.println("Rechnung angelegt");
		}
	}
	
	public void initialize() {
	}
	
	public void setzeRechnung(Rechnung p) {
		nameFeld.setText("");
		auftraggeberFeld.setText("");
		kasseFeld.setText("");
		artFeld.setText("");
		datumFeld.setText("");
		ansprechpartnerFeld.setText("");
		topfFeld.setText("");
		kontoFeld.setText("");
	}
}
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

public class ControllerBauteilEingabe {
	
	
	@FXML
	private TextField nameFeld;
	@FXML
	private TextField linkFeld;
	@FXML
	private TextField preisFeld;
	@FXML
	private TextField lagerortFeld;
	@FXML
	private TextField lagerndFeld;
	@FXML
	private TextField bestelltFeld;
	@FXML
	private TextField geplantFeld;
	@FXML
	private ComboBox<String> comboKategorie;
	
	@FXML
	private Button speichernButtonBauteil;
	@FXML
	void bauteilSpeichern(ActionEvent event) {
		String name = nameFeld.getText();
		String link = linkFeld.getText();
		String preis = preisFeld.getText();
		String lagerort = lagerortFeld.getText();
		String lagernd = lagerndFeld.getText();
		String bestellt = bestelltFeld.getText();
		String geplant = geplantFeld.getText();
		String kategorie = comboKategorie.getValue();
		
		if(name.isEmpty() || link.isEmpty()
				|| preis.isEmpty() || lagerort.isEmpty()
				|| lagernd.isEmpty() || bestellt.isEmpty() 
				|| geplant.isEmpty() || kategorie.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			long time = System.currentTimeMillis();
			Bauteil b = new Bauteil(name,link,2,2,2,2);
			Verwaltung verwaltung = new Verwaltung();
			//verwaltung.addPerson(b);
			((Node)(event.getSource())).getScene().getWindow().hide();
			System.out.println("Person angelegt");
		}
	}
	
	public void initialize() {
	}
	
	public void setzeBauteil(Bauteil b) {
		nameFeld.setText(b.getName());
		linkFeld.setText(b.getLink());
		//lagerortFeld.setText(b.);
		//lagerndFeld.setText(b.getBestandLager());
		//bestelltFeld.setText(b.getBestandBestellt());
		//geplantFeld.setText(p.getTelefon());
		//preisFeld.setText(p.getEmail());
		//nummerFeld.setText(p.getHausnummer());
	}
}

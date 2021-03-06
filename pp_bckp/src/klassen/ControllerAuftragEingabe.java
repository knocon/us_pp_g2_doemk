
package klassen;

import com.sun.glass.ui.Application;

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
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class ControllerAuftragEingabe {
	//ortAuftrag 
	@FXML
	private TextField titelFeld;
	@FXML
	private TextField dateiFeld;
	@FXML
	private TextField pKostenFeld;
	@FXML
	private TextField reeleKFeld;
	@FXML
	private TextField artFeld;
	@FXML
	private TextField ortFeld;
	
	@FXML
	ComboBox<String> artComboBox;
	
	
	double rKosten;
	double pKosten;
	
	@FXML
	private Button speichernButtonAuftrag;
	@FXML
	void auftragSpeichern(ActionEvent event) {
		String titel = titelFeld.getText();
		String datei = dateiFeld.getText();
		String pKosten = pKostenFeld.getText();
		String art = artComboBox.getValue();
		//double Pkosten = Double.parseDouble(pKosten);
		String rKosten = reeleKFeld.getText();
		//double Rkosten = Double.parseDouble(rKosten);
		
		String typ = ortFeld.getText();
		
		if(titel.isEmpty() || datei.isEmpty()
				|| pKosten.isEmpty() || rKosten.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			if(validate()) {
			long time = System.currentTimeMillis();
			Auftrag a = new Auftrag(1, titel, art, datei, rKosten, pKosten, time,typ);
			Verwaltung verwaltung = new Verwaltung();
			verwaltung.addAuftrag(a);
			((Node)(event.getSource())).getScene().getWindow().hide();
			System.out.println("Auftrag angelegt");
			}
		}
	}
	
	public void initialize() {
		load();
	}
	
	public void load() {		
		ObservableList<String> art = FXCollections.observableArrayList("Leiterplatte", "3D-Druck", "Sonstiges");
		artComboBox.setItems(art);
	}
	public void setzeAuftrag(Auftrag a) {
		titelFeld.setText(a.getTitel());
		dateiFeld.setText(a.getDateiname());
		//double pKosten = a.getPkosten();
		String Pkosten = String.valueOf(pKosten);
		pKostenFeld.setText( a.getPkosten());
		//double rKosten = a.getRkosten();
		String Rkosten = String.valueOf(a.getRkosten());
		reeleKFeld.setText(Rkosten);
		artFeld.setText(a.getArt());
		ortFeld.setText(a.getDateiname());
	}
	
	public boolean validate(){
		
		
		try{
			pKosten = Double.parseDouble(pKostenFeld.getText());
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe beim pKosten", ButtonType.OK);
			alert.showAndWait();
			return false;
		}
		try{
			rKosten = Double.parseDouble(reeleKFeld.getText());
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe beim rKosten", ButtonType.OK);
			alert.showAndWait();
			return false;
		}
		
		return true;
		
	}
	
	
}
package klassen;

import java.sql.SQLException;
import java.util.Iterator;

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

public class ControllerAuftragBetreuer {
	//
	
	@FXML
	private TextField nameText;
	@FXML
	private TextField iDText;
	
	private Auftrag a;
	int id;
	@FXML
	private Button speichern;
	@FXML
	void speicherngedrueckt(ActionEvent event) throws SQLException {
		
		String name = nameText.getText();
		String id = iDText.getText();
		//int Id = Integer.parseInt(id);
		Verwaltung verwaltung = new Verwaltung();
		int id1 = a.getAufId();
		String id2 = String.valueOf(id1);
		//ObservableList<Auftrag> listPersonen
		if(validate()) {
		if(verwaltung.abfrageBetreuer(id)) {
		ObservableList<Auftrag> listPersonen =verwaltung.filterByParameterAuftrag("aufId", id2 , "Auftrag");
		Iterator<Auftrag> it = listPersonen.iterator();
		while ( it.hasNext()) {
			Auftrag auf = it.next();
			
				if(verwaltung.peronDa(name, id)) {
					System.out.println("HALLO");
			verwaltung.betreuerHinzufügen(name, id, a.getTitel(), a.getAufId());
			}
		}}else {
			Alert abfrage = new Alert(AlertType.ERROR, "Diese Person ist kein Betruer!",
					ButtonType.OK);
			abfrage.showAndWait();
		}
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	}
	public void setzeAuftrag(Auftrag a) {
		this.a = a;
	}
public boolean validate(){
		
		
		try{
			id = Integer.parseInt(iDText.getText());
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe bei ID!", ButtonType.OK);
			alert.showAndWait();
			return false;
		}
		return true;
	}
}

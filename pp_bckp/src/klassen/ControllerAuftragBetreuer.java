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
	
	@FXML
	private Button speichern;
	@FXML
	void speicherngedrueckt(ActionEvent event) throws SQLException {
		
		String name = nameText.getText();
		String id = iDText.getText();
		int Id = Integer.parseInt(id);
		Verwaltung verwaltung = new Verwaltung();
		int id1 = a.getAufId();
		String id2 = String.valueOf(id1);
		//ObservableList<Auftrag> listPersonen
		ObservableList<Auftrag> listPersonen =verwaltung.filterByParameterAuftrag("aufId", id2 , "Auftrag");
		Iterator<Auftrag> it = listPersonen.iterator();
		while ( it.hasNext()) {
			Auftrag auf = it.next();
			
				//if(verwaltung.peronDa(name, Id)) {
					System.out.println("HALLO");
			verwaltung.betreuerHinzufügen(name, Id, a.getTitel(), a.getAufId());
			//}
		}
	}
	
	public void setzeAuftrag(Auftrag a) {
		this.a = a;
	}
}

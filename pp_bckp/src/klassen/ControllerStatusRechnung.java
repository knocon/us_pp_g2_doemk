package klassen;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerStatusRechnung {
	
	@FXML
	private Label datum1;
	@FXML
	private Label datum2;
	@FXML
	private Label datum3;
	@FXML
	private Label datum4;
	
	@FXML
	private Button schliessenButton;
	@FXML
	void schliessen(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public void setStatus(Rechnung r) {
		Verwaltung verwaltungRech = new Verwaltung();
		ArrayList<statusRechnung> sa=  verwaltungRech.filterDatumRech("rechId", r.getRechId(),"DatumRechnung");
		Iterator<statusRechnung> it = sa.iterator();
		
		while ( it.hasNext()) {
			statusRechnung nue = it.next();
			if(nue.getRechId()==r.getRechId()) {
				datum1.setText(nue.getBearbeitung());
				datum2.setText(nue.getEingereicht());
				datum3.setText(nue.getAbgewickelt());
				datum4.setText(nue.getAusstehend());
				
			}
		}
		
		
		//  Hier müssen die Daten an die Labels übergeben werden
		
	}
	
	public void initialize() {
	}
}

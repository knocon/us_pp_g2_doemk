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
	
	public void setStatus(Auftrag a) {
		Verwaltung verwaltung = new Verwaltung();
		ArrayList<StatusAuftrag> sa=  verwaltung.filterDatum("aufId", a.getAufId(), "DatumAuftrag");
		Iterator<StatusAuftrag> it = sa.iterator();
		
		while ( it.hasNext()) {
			StatusAuftrag nue = it.next();
			if(nue.getId()==a.getAufId()) {
				datum1.setText(nue.getAngenommen());
				datum2.setText(nue.getGefertigt());
				datum3.setText(nue.getKostenK());
				datum4.setText(nue.getAbgeholt());
				datum5.setText(nue.getAbgerechnet());
				datum6.setText(nue.getWarten());
				datum7.setText(nue.getFertigungU());
			}
		}
		
	}
	
	public void initialize() {
	}
}

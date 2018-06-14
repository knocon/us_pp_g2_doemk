package klassen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javafx.scene.control.Alert.AlertType;

public class ControllerRechnungEingabe {
	static Connection conn = null;
	static ResultSet resultSet;
	static ResultSet resultSetLeer;
	static ResultSet rs;
	static java.sql.Statement statement;
	static PreparedStatement pst = null;
	static LocalDate date;
	static ObservableList<Person> listPerson;
	static ObservableList<Rechnung> listRechnung;
	static ObservableList<Bauteil> listBauteil;
	static ObservableList<Bauteil> listBauteilWk;
	static ObservableList options = FXCollections.observableArrayList();
	static ObservableList leer = FXCollections.observableArrayList();
	
	private double epreis;
	int konto;
	int kasse;
	
	String kategorie = "";
	
	@FXML
	private TextField nameFeld;
	@FXML
	private TextField auftraggeberFeld;
	@FXML
	private TextField kasseFeld;
	@FXML
	private TextField artFeld;
	
	@FXML
	private TextField ansprechpartnerFeld;
	@FXML
	private ComboBox <String> topfComboBox;
	@FXML
	private TextField kontoFeld;
	
	@FXML
	private TextField betragFeld;
	
	@FXML
	private Button speichernButtonRechnung;
	@FXML
	void rechnungSpeichern(ActionEvent event) {
		String topf = topfComboBox.getValue();
		String name = nameFeld.getText();
		String auftraggeber = auftraggeberFeld.getText();
		String kasse = kasseFeld.getText();
		String art = artFeld.getText();
		String ansprechpartner = ansprechpartnerFeld.getText();
	
		String kontoId = kontoFeld.getText();
		String betrag = betragFeld.getText();
		
		if(name.isEmpty() || auftraggeber.isEmpty()
				|| kasse.isEmpty() || art.isEmpty()
				 || ansprechpartner.isEmpty() 
				 || kontoId.isEmpty() || betrag.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else if(validate()) {
			long time = System.currentTimeMillis();
			Rechnung r = new Rechnung(1,time, name, auftraggeber, ansprechpartner, kasse, topf, art, kontoId, betrag, null);
			Verwaltung verwaltung = new Verwaltung();
			verwaltung.addRechnung(r);
			((Node)(event.getSource())).getScene().getWindow().hide();
			System.out.println("Rechnung angelegt");
		}
	}
	
	public void initialize() {
		options.setAll(leer);
		load();
	}
	
	public void setzeRechnung(Rechnung p) {
		nameFeld.setText(p.getRechnungsName());
		auftraggeberFeld.setText(p.getAuftraggeber());
		kasseFeld.setText(p.getKassenId());
		artFeld.setText(p.getArt());
		ansprechpartnerFeld.setText(p.getAnsprechpartner());
		
		kontoFeld.setText(p.getKontoId());
		betragFeld.setText(p.getBetrag());
	}
	public void setzeRechnungAufrag(Auftrag a) {
		nameFeld.setText(a.getTitel());
		//double kosten = a.getRkosten();
		//String kostennue = String.valueOf(kosten);
		betragFeld.setText(a.getRkosten());
	}
	public void load() {
		try {
			Connection con = Verwaltung.dbconnection();
			String query = "SELECT DISTINCT name FROM Topf";
			pst = con.prepareStatement(query);
			resultSet = pst.executeQuery();
			while(resultSet.next()) {
				options.add(resultSet.getString("name"));
			}
			resultSet = resultSetLeer;
		}catch(SQLException ex) {
			System.out.println("LOL");
		}
		topfComboBox.setItems(options);
	}
	
public boolean validate(){
		
		
		try{
			epreis = Double.parseDouble(betragFeld.getText());
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe beim Betrag", ButtonType.OK);
			alert.showAndWait();
			return false;
		}
		
		try{
			konto = Integer.parseInt(kontoFeld.getText());
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe beim Konto!", ButtonType.OK);
			alert.showAndWait();
			return false;
		}
		
		try{
			kasse = Integer.parseInt(kasseFeld.getText());
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe bei Kasse!", ButtonType.OK);
			alert.showAndWait();
			return false;
		}
		
		
		
		 
	     
	return true;
	
}



}

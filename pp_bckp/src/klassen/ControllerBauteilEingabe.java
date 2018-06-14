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

public class ControllerBauteilEingabe{
		
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
	int bestandLager;
	int bestandBestellt;
	int bestandGeplant;
	String kategorie = "";
	
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

		try{
			String test = comboKategorie.getSelectionModel().getSelectedItem().toString();
			kategorie = test;
		}catch(Exception e){

		}	
		
		if(name.isEmpty() || link.isEmpty()
				|| preis.isEmpty() || lagerort.isEmpty()
				|| lagernd.isEmpty() || bestellt.isEmpty() 
				|| geplant.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}else if(validate()){
			

			//long time = System.currentTimeMillis();
			Bauteil b = new Bauteil(name, kategorie, link, epreis, lagerort, bestandLager, bestandBestellt, bestandGeplant, 0);
			Verwaltung verwaltung = new Verwaltung();
			verwaltung.addBauteil(b);
			System.out.println(b.getTeilId());
			((Node)(event.getSource())).getScene().getWindow().hide();
			System.out.println("Bauteil angelegt");}
		
	}
	
	
	public void initialize() {

				options.setAll(leer);
				load();
			
		
	}
	
	public void setzeBauteil(Bauteil b) {
		nameFeld.setText(b.getName());
		linkFeld.setText(b.getLink());
		preisFeld.setText(b.getEpreisString());
		lagerortFeld.setText(b.getLagerort());
		lagerndFeld.setText(b.getLagerString());
		bestelltFeld.setText(b.getBestelltString());
		geplantFeld.setText(b.getGeplantString());

	}


	public ComboBox<String> getComboKategorie() {
		return comboKategorie;
	}


	public void setComboKategorie(ComboBox<String> comboKategorie) {
		this.comboKategorie = comboKategorie;
	}
	
	public void load() {
		try {
			Connection con = Verwaltung.dbconnection();
			String query = "SELECT DISTINCT name FROM Kategorie";
			pst = con.prepareStatement(query);
			resultSet = pst.executeQuery();
			while(resultSet.next()) {
				options.add(resultSet.getString("name"));
			}
			resultSet = resultSetLeer;
		}catch(SQLException ex) {
			System.out.println("LOL");
		}
		comboKategorie.setItems(options);
	}

	public boolean validate(){
		
		
			try{
				epreis = Double.parseDouble(preisFeld.getText());
			}catch(Exception e){
				Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe beim Preis", ButtonType.OK);
				alert.showAndWait();
				return false;
			}
			
			try{
				bestandLager = Integer.parseInt(lagerndFeld.getText());
			}catch(Exception e){
				Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe beim Lagerbestand!", ButtonType.OK);
				alert.showAndWait();
				return false;
			}
			
			try{
				bestandBestellt = Integer.parseInt(bestelltFeld.getText());
			}catch(Exception e){
				Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe beim bestellten Lagerbestand!", ButtonType.OK);
				alert.showAndWait();
				return false;
			}
			
			try{
				bestandGeplant = Integer.parseInt(geplantFeld.getText());
			}catch(Exception e){
				Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe beim geplanten Lagerbestand!", ButtonType.OK);
				alert.showAndWait();
				return false;
			}
			
			
			 Pattern p = Pattern.compile("[^A-Za-z0-9]");
		     Matcher m = p.matcher(lagerortFeld.getText());
		     boolean b = m.find();
		     
		     if(b){
		    	 Alert alert = new Alert(AlertType.ERROR,"Fehlerhafte Eingabe beim Lagerort", ButtonType.OK);
					alert.showAndWait();
		    	 return false;
		     }	
		return true;
		
	}



}

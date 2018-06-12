package klassen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
	static ResultSet rs;
	static java.sql.Statement statement;
	static PreparedStatement pst = null;
	static LocalDate date;
	static ObservableList<Person> listPerson;
	static ObservableList<Rechnung> listRechnung;
	static ObservableList<Bauteil> listBauteil;
	static ObservableList<Bauteil> listBauteilWk;
	static ObservableList options = FXCollections.observableArrayList();
	static int refresh = 1;
	
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
		double epreis = Double.parseDouble(preis);
		
		String lagerort = lagerortFeld.getText();
		
		String lagernd = lagerndFeld.getText();
		int bestandLager = Integer.parseInt(lagernd);
		
		String bestellt = bestelltFeld.getText();
		int bestandBestellt = Integer.parseInt(bestellt);
		
		String geplant = geplantFeld.getText();
		int bestandGeplant = Integer.parseInt(geplant);
		
		String kategorie = comboKategorie.getSelectionModel().getSelectedItem().toString();
		
		if(name.isEmpty() || link.isEmpty()
				|| preis.isEmpty() || lagerort.isEmpty()
				|| lagernd.isEmpty() || bestellt.isEmpty() 
				|| geplant.isEmpty() || kategorie.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			//long time = System.currentTimeMillis();
			Bauteil b = new Bauteil(name, kategorie, link, epreis, lagerort, bestandLager, bestandBestellt, bestandGeplant, 0);
			Verwaltung verwaltung = new Verwaltung();
			verwaltung.addBauteil(b);
			((Node)(event.getSource())).getScene().getWindow().hide();
			System.out.println("Bauteil angelegt");
		}
	}
	
	
	public void initialize() {

			if(refresh==1) {
				load();
			}else {
				comboKategorie.setItems(options);
			}
			
		
	}
	
	public void setzeBauteil(Bauteil b) {
		nameFeld.setText(b.getName());
		linkFeld.setText(b.getLink());
		//preisFeld.setText(b.getEpreis());
		lagerortFeld.setText(b.getLagerort());
		//lagerndFeld.setText(b.getBestandLager());
		//bestelltFeld.setText(b.getTelefon());
		//geplantFeld.setText(b.getEmail());
		//comboKategorie.setText(b.getHausnummer());

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
			String query = "SELECT name FROM Kategorie";
			pst = con.prepareStatement(query);
			resultSet = pst.executeQuery();
			while(resultSet.next()) {
				options.add(resultSet.getString("name"));
			}
			pst.close();
			resultSet.close();
		}catch(SQLException ex) {
			System.out.println("LOL");
		}
		
		comboKategorie.setItems(options);
		refresh = 0;
	}
	

}

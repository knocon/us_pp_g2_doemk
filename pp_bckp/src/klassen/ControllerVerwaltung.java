package klassen;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerVerwaltung{
		
	static ResultSet resultSet;
	static ResultSet rs;
	static java.sql.Statement statement;
	static ObservableList<BauteileRechnung> listRech;
	static Connection conn = null;
	
	@FXML
	private TableView<BauteileRechnung> brechTable;
	@FXML
	private TableColumn<BauteileRechnung, String> rechId;
	@FXML
	private TableColumn<BauteileRechnung, String> datum;
	@FXML
	private TableColumn<BauteileRechnung, String> summe;
	@FXML
	private TableColumn<BauteileRechnung, String> kaeufer;
	@FXML
	private TableColumn<BauteileRechnung, String> status;
	@FXML
	private Button bezahltButton;

	@FXML
	private Button ausstehendButton;

	@FXML
	void bezahltAction(ActionEvent event) {
		
		BauteileRechnung w = brechTable.getSelectionModel().getSelectedItem();
		
		try{
			Verwaltung.setzeBezahlt(w);
		}catch(NullPointerException npe) {
			Alert abfrage = new Alert(AlertType.ERROR, "Bitte Tupel aus dem Datenbestand wählen!", ButtonType.OK);
			abfrage.showAndWait();
		}
		
		try {
			Connection con = Verwaltung.dbconnection();
			listRech = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM BauteilRechnung");
			while (rs.next()) {
				listRech.add(new BauteileRechnung(rs.getInt("brId"),rs.getString("datum"),rs.getDouble("summe"),rs.getString("kaeuferId"),rs.getString("status")));
			
			}
		}catch (SQLException ex) {
			System.out.println("error");
		}
		
		rechId.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("brId"));
		datum.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("datum"));
		summe.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("summe"));
		kaeufer.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("kaeuferId"));
		status.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("status"));
		
		brechTable.setItems(listRech);
	}
	

	@FXML
	void ausstehendAction(ActionEvent event) {
		
BauteileRechnung w = brechTable.getSelectionModel().getSelectedItem();
		
		try{
			Verwaltung.setzeAusstehend(w);
		}catch(NullPointerException npe) {
			Alert abfrage = new Alert(AlertType.ERROR, "Bitte Tupel aus dem Datenbestand wählen!", ButtonType.OK);
			abfrage.showAndWait();
		}
		
		try {
			Connection con = Verwaltung.dbconnection();
			listRech = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM BauteilRechnung");
			while (rs.next()) {
				listRech.add(new BauteileRechnung(rs.getInt("brId"),rs.getString("datum"),rs.getDouble("summe"),rs.getString("kaeuferId"),rs.getString("status")));
			
			}
		}catch (SQLException ex) {
			System.out.println("error");
		}
		
		rechId.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("brId"));
		datum.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("datum"));
		summe.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("summe"));
		kaeufer.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("kaeuferId"));
		status.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("status"));
		brechTable.setItems(listRech);

	}

	
	public void initialize() {

		try {
			Connection con = Verwaltung.dbconnection();
			listRech = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM BauteilRechnung");
			while (rs.next()) {
				listRech.add(new BauteileRechnung(rs.getInt("brId"),rs.getString("datum"),rs.getDouble("summe"),rs.getString("kaeuferId"),rs.getString("status")));
			
			}
		}catch (SQLException ex) {
			System.out.println("error");
		}
		
		rechId.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("brId"));
		datum.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("datum"));
		summe.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("summe"));
		kaeufer.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("kaeuferId"));
		status.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("status"));
		
		brechTable.setItems(listRech);
	}



}

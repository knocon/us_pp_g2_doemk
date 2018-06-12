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
		
		BauteilWk w = warenkorbTable.getSelectionModel().getSelectedItem();
		
		if(	w!=null) {
			Verwaltung.deleteWarenkorb(w.getId());
			// UNWICHTIG - AKTUALISIERUNG
			try {
				Connection con = Verwaltung.dbconnection();
				listWarenkorb = FXCollections.observableArrayList();
				ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Warenkorb");
				while (rs.next()) {
					System.out.println("yop");
					listWarenkorb.add(new BauteilWk(rs.getInt("id"),rs.getInt("bauteilId"),rs.getString("bauteilName"),rs.getDouble("preis")));
				
				}
			}catch (SQLException ex) {
				System.out.println("error");
			}
			
			warenkorbId.setCellValueFactory(new PropertyValueFactory<BauteilWk, String>("id"));
			warenkorbName.setCellValueFactory(new PropertyValueFactory<BauteilWk, String>("name"));
			warenkorbPreis.setCellValueFactory(new PropertyValueFactory<BauteilWk, String>("epreis"));
			System.out.println(listWarenkorb.toString());
			warenkorbTable.setItems(listWarenkorb);	
			
			Verwaltung.inkrementLager(w.getTeilId());			
		}
	}

	@FXML
	void ausstehendAction(ActionEvent event) {

	}

	
	public void initialize() {

		try {
			Connection con = Verwaltung.dbconnection();
			listRech = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Warenkorb");
			while (rs.next()) {
				System.out.println("yop");
				listRech.add(new BauteileRechnung(rs.getInt("brId"),rs.getDate("datum"),rs.getDouble("summe"),rs.getString("kaeuferId"),rs.getString("status")));
			
			}
		}catch (SQLException ex) {
			System.out.println("error");
		}
		
		rechId.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("id"));
		datum.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("name"));
		summe.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("epreis"));
		kaeufer.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("epreis"));
		status.setCellValueFactory(new PropertyValueFactory<BauteileRechnung, String>("epreis"));
		
		System.out.println(listRech.toString());
		brechTable.setItems(listRech);
	}



}

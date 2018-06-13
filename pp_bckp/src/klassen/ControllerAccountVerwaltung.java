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

public class ControllerAccountVerwaltung{
		
	static ResultSet resultSet;
	static ResultSet rs;
	static java.sql.Statement statement;
	static ObservableList<Account> listAcc;
	static Connection conn = null;
	
	@FXML
	private TableView<Account> accountTable;
	@FXML
	private TableColumn<Account, String> rolleFeld;
	@FXML
	private TableColumn<Account, String> vornameFeld;
	@FXML
	private TableColumn<Account, String> nameFeld;
	
	@FXML
	private Button loeschenButton;
	@FXML
	void loeschen(ActionEvent event) {
		
		Account acc = accountTable.getSelectionModel().getSelectedItem();
		
	}
	
	public void initialize() {
		rolleFeld.setCellValueFactory(
                new PropertyValueFactory<Account, String>("rolle"));
		vornameFeld.setCellValueFactory(
                new PropertyValueFactory<Account, String>("name"));
		nameFeld.setCellValueFactory(
                new PropertyValueFactory<Account, String>("titel"));
		
		
		try {
			Connection con = Verwaltung.dbconnection();
			listAcc = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Account");
			while (rs.next()) {
				listAcc.add(new Account(rs.getInt("accId"),
						rs.getString("username"),
						rs.getString("pw"),
						rs.getString("rolle")));
			}
			
			accountTable.setItems(listAcc);
			
		}catch (SQLException ex) {
			System.out.println("error");
		}
		
	}



}

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
	private TableColumn<Account, String> nachnameFeld;
	@FXML
	private TableColumn<Account, String> nameFeld;
	@FXML
	private TableColumn<Account, String> emailFeld;
	
	@FXML
	private Button loeschenButton;
	@FXML
	void loeschen(ActionEvent event) {
		int accountLevel = Controller.getEingeloggterAccount();
		if(accountLevel!=2) {
			Alert alert = new Alert(AlertType.ERROR,"Sie müssen als Verwalter eingeloggt sein.", ButtonType.OK);
			alert.show();
		}
		else
		{
			Account acc = accountTable.getSelectionModel().getSelectedItem();
			if(acc==null) {
				Alert alert = new Alert(AlertType.ERROR,"Sie müssen einen Account auswählen.", ButtonType.OK);
				alert.show();
			}
			else {
				Alert alert = new Alert(AlertType.CONFIRMATION,"Wollen Sie den Account wirklich löschen?", ButtonType.YES, ButtonType.NO);
				alert.showAndWait();
				if(alert.getResult()== ButtonType.YES) {
					AccountLogik accLogik = new AccountLogik();
					accLogik.loescheAccount(acc);
				}
			}
		}
		ladeTabelle();
	}
	
	@FXML
	private Button aendernButton;
	@FXML
	void aendern(ActionEvent event) {
		Account account = accountTable.getSelectionModel().getSelectedItem();
		if(account!=null) {
			System.out.println(account.getRolle());
			if(account.getRolle().equals("Benutzer")) {
				Alert abfrage = new Alert(AlertType.CONFIRMATION, "Wollen Sie den Account wirklich aufstufen?",ButtonType.YES, ButtonType.NO);
				abfrage.showAndWait();
				if(abfrage.getResult()==ButtonType.YES) {
					AccountLogik accLogik = new AccountLogik();
					accLogik.aendereRolle(account);
				}
				else {
					
				}
			}
			else {
				Alert abfrage = new Alert(AlertType.ERROR, "Account ist bereits Verwalter",ButtonType.OK);
				abfrage.showAndWait();
			}
		}
		else {
			Alert abfrage = new Alert(AlertType.ERROR, "Wählen Sie eine Zeile in der Tabelle aus", ButtonType.OK);
			abfrage.show();
		}
		ladeTabelle();
	}
	
	public void initialize() {
		rolleFeld.setCellValueFactory(
                new PropertyValueFactory<Account, String>("rolle"));
		vornameFeld.setCellValueFactory(
                new PropertyValueFactory<Account, String>("vorname"));
		nachnameFeld.setCellValueFactory(
                new PropertyValueFactory<Account, String>("nachname"));
		nameFeld.setCellValueFactory(
                new PropertyValueFactory<Account, String>("name"));
		emailFeld.setCellValueFactory(
                new PropertyValueFactory<Account, String>("email"));
		
		ladeTabelle();
	}

	public void ladeTabelle() {
		try {
			Connection con = Verwaltung.dbconnection();
			listAcc = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Account"
					+ " INNER JOIN Person ON Account.persId = Person.persId");
			while (rs.next()) {
				listAcc.add(new Account(
						rs.getString("username"),
						rs.getString("pw"),
						rs.getString("rolle"),
						rs.getString("vorname"),
						rs.getString("nachname"),
						rs.getString("email")));
			}
			
			accountTable.setItems(listAcc);
			
		}catch (SQLException ex) {
			ex.printStackTrace();
			Alert abfrage = new Alert(AlertType.ERROR, "SQL ERROR: Tabelle konnte nicht geladen werden.",ButtonType.OK);
			abfrage.showAndWait();
		}
	}

}

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

public class ControllerWarenkorb{
		
	static ResultSet resultSet;
	static ResultSet rs;
	static java.sql.Statement statement;
	static ObservableList<BauteilWk> listWarenkorb;
	static Connection conn = null;
	
	@FXML
	private TableView<BauteilWk> warenkorbTable;
	@FXML
	private TableColumn<BauteilWk, String> warenkorbId;
	@FXML
	private TableColumn<BauteilWk, String> warenkorbName;
	@FXML
	private TableColumn<BauteilWk, String> warenkorbPreis;
	@FXML
	private Button entfernenButton;
	@FXML
	private Button leerenButton;
	@FXML
	private Button stellenButton;

	@FXML
	void entfernenAction(ActionEvent event) {
		ladeAlleWarenkorb();
	}

	@FXML
	void leerenAction(ActionEvent event) {

	}

	@FXML
	void stellenAction(ActionEvent event) {

	}

	
	public void initialize() {
//		try {
			
//			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Warenkorb");
//			while (rs.next()) {
//				System.out.println("yes");
//					list.add(new BauteilWk(rs.getInt("teilId"),rs.getString("name"),rs.getDouble("epreis")));
//
//			
//			}
//		}catch(SQLException ex) {
//			
//		}


		
		
		try {
			Connection con = Verwaltung.dbconnection();
			listWarenkorb = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Warenkorb");
			while (rs.next()) {
				System.out.println("yop");
				listWarenkorb.add(new BauteilWk(rs.getInt("bauteilId"),rs.getString("bauteilName"),rs.getDouble("preis")));
			
			}
		}catch (SQLException ex) {
			System.out.println("error");
		}
		
		warenkorbId.setCellValueFactory(new PropertyValueFactory<BauteilWk, String>("teilId"));
		warenkorbName.setCellValueFactory(new PropertyValueFactory<BauteilWk, String>("name"));
		warenkorbPreis.setCellValueFactory(new PropertyValueFactory<BauteilWk, String>("epreis"));
		System.out.println(listWarenkorb.toString());
		warenkorbTable.setItems(listWarenkorb);
	}
	
	public ObservableList<BauteilWk> getWk(ResultSet rs) throws SQLException {
		try {
			listWarenkorb = FXCollections.observableArrayList();
			while (rs.next()) {
				BauteilWk b = new BauteilWk(rs.getInt("bauteilId"),rs.getString("bauteilName"),rs.getDouble("preis"));
				listWarenkorb.add(b);
			}
			return listWarenkorb;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ObservableList<BauteilWk> ladeWarenkorb() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Warenkorb");
			ObservableList<BauteilWk> baut = getWk(resultSet);
			return baut;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void ladeAlleWarenkorb() {
		ObservableList<BauteilWk> bauteile = ladeWarenkorb();
		warenkorbTable.setItems(bauteile);
	}


}

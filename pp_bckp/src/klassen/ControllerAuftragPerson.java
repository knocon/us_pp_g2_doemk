package klassen;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerAuftragPerson {
	/*static ResultSet resultSet;
	static ResultSet rs;
	static java.sql.Statement statement;
	static ObservableList<Auftrag> listRech;
	static Connection conn = null;*/

	static ResultSet resultSet;
	static ResultSet rs;
	static java.sql.Statement statement;
	static ObservableList<VTAuftragPerson> listmeineRech;
	static Connection conn = null;
	
	@FXML
	private TableView<VTAuftragPerson> perTabelle;
	@FXML
	private TableColumn<VTAuftragPerson, String> aufID;
	@FXML
	private TableColumn<VTAuftragPerson, String> aufName;
	@FXML
	private TableColumn<VTAuftragPerson, String> person;
	@FXML
	private TableColumn<VTAuftragPerson, String> id;
	
	private Auftrag a;
	

	@FXML
	private Button hinzufuegenButton;

	@FXML
	void hinzuAction(ActionEvent event) {
		
		try {
			neuesFenster("/gui/betreuer.fxml", "Betreuer");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		private void neuesFenster(String fxml, String fensterTitel) {
			Stage dialog = new Stage();
			Parent page;
			try {
				page = FXMLLoader.load(getClass().getResource(fxml));
				Scene scene = new Scene(page);
				dialog.setScene(scene);
				dialog.setTitle(fensterTitel);
				dialog.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		public void setzteAuftrag(Auftrag a) {
			this.a = a;
			System.out.println(a.getAufId());
			try {
				Connection con = Verwaltung.dbconnection();
				listmeineRech = FXCollections.observableArrayList();
				System.out.println(a.getAufId());
				ResultSet rs = con.createStatement().executeQuery("SELECT * FROM VTAuftragPerson WHERE aufId ='"+ a.getAufId()+"'");
				while (rs.next()) {
					listmeineRech.add(new VTAuftragPerson(rs.getInt("aufId"), rs.getString("aufName"), rs.getInt("perId"), rs.getString("perName")));
				
				}
			}catch (SQLException ex) {
				System.out.println("error");
			}
			
			
			aufID.setCellValueFactory(new PropertyValueFactory<VTAuftragPerson, String>("aufId"));
			aufName.setCellValueFactory(new PropertyValueFactory<VTAuftragPerson, String>("aufName"));
			person.setCellValueFactory(new PropertyValueFactory<VTAuftragPerson, String>("perName"));
			id.setCellValueFactory(new PropertyValueFactory<VTAuftragPerson, String>("perId"));
			
			perTabelle.setItems(listmeineRech);
		}
		
		
		
}

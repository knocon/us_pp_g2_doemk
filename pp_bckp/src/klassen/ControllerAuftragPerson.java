package klassen;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControllerAuftragPerson {
	/*static ResultSet resultSet;
	static ResultSet rs;
	static java.sql.Statement statement;
	static ObservableList<Auftrag> listRech;
	static Connection conn = null;*/

	@FXML
	private TableView<Auftrag> perTabelle;
	@FXML
	private TableColumn<Auftrag, String> aufId;
	@FXML
	private TableColumn<Auftrag, String> aufName;
	@FXML
	private TableColumn<Auftrag, String> person;
	@FXML
	private TableColumn<Auftrag, String> id;
	
	

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
		
		
}}

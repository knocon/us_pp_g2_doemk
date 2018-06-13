package klassen;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
		
}}

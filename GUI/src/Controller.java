import com.sun.xml.internal.bind.v2.schemagen.episode.Package;
import java.io.IOException;

import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class Controller extends Application {
	
	public static void main(String[] args){
		Application.launch(Controller.class, args);
    }
	
	@FXML
	private TableView<Person> tableView = new TableView<Person>();
	@FXML
	private TableColumn<Person, String> vornameCol = new TableColumn<Person, String>("Vorname");
	
	
	public Controller(){
		System.out.println("asd");
		schreibeWerte();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			Parent root = FXMLLoader.load(getClass().getResource("gui/GUI.fxml"));
			System.out.println("??");
			Scene scene = new Scene(root);
			primaryStage = new Stage();
			primaryStage.setTitle("Bestes Verwaltungsprogramm aller Zeiten!");
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("??");
	}
	
	public void schreibeWerte() {
		ObservableList<Person> pList = FXCollections.observableArrayList(
				new Person ("aaaa", "bbbbb", "s","straße","9","stadt","123","am@asd.de"));
		vornameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("vorname"));
		tableView.setItems(pList);
	}
	
}

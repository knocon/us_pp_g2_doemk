package klassen;
import com.sun.xml.internal.bind.v2.schemagen.episode.Package;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class Controller extends Application {
	
	public static void main(String[] args){
		Application.launch(Controller.class, args);
    }
	
	@FXML
	private TableView<Person> tableView;
	@FXML
	private TableColumn<Person, String> vornameCol;
	@FXML
	private TableColumn<Person, String> nachnameCol;
	@FXML
	private TableColumn<Person, String> emailCol;
	@FXML
	private TableColumn<Person, String> rolleCol;
	@FXML
	private TableColumn<Person, String> telefonCol;
	@FXML
	private TableColumn<Person, String> stempelCol;
	@FXML
	private TableColumn<Person, String> adresseCol;
	@FXML
	private TableColumn<Person, String> stadtCol;
	@FXML
	private TableColumn<Person, String> plzCol;
	@FXML
	private TableColumn<Person, String> nummerCol;
	
	@FXML
	private Button anlegenButtonPerson;
	@FXML
	void anlegenGeklicktPerson(ActionEvent event) {
		System.out.println("Person angelegt");
		try {
			newDialogWindow("/gui/personen_eingabe.fxml");
			initTabelle();
			schreibeDummis();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button bearbeitenButtonPerson;
	@FXML
	void bearbeitenGeklicktPerson(ActionEvent event) {
		tableView.setEditable(true);
		System.out.println("Bearbeitet");
	}
	
	@FXML
	private Button loeschenButtonPerson;
	@FXML
	void loeschenGeklicktPerson(ActionEvent event) {
		System.out.println("Geloescht");
	}
	
	// Personen Eingabe Fenster
	@FXML
	private TextField vornameFeld;
	@FXML
	private TextField nachnameFeld;
	@FXML
	private TextField rolleFeld;
	@FXML
	private TextField strasseFeld;
	@FXML
	private TextField stadtFeld;
	@FXML
	private TextField plzFeld;
	@FXML
	private TextField telefonFeld;
	@FXML
	private TextField emailFeld;
	
	@FXML
	private Button speichernButtonPersonen;
	@FXML
	void personSpeichern(ActionEvent event) {
		System.out.println("Person gespeichert");
		//dialog.close();
	}
	
	public Controller(){
		Stage dialog;
		System.out.println("asd");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/GUI.fxml"));
			System.out.println("?");
			Scene scene = new Scene(root);
			primaryStage = new Stage();
			primaryStage.setTitle("Bestes Verwaltungsprogramm aller Zeiten!");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("??");
	}
	
	private void newDialogWindow(String fxml) throws Exception{
        Stage dialog = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(page);
        dialog.setScene(scene);
        dialog.show();
    }
	
	private void closeDialogWindow() {
		dialog.close();
	}
	
	public void initTabelle() {
		vornameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("vorname"));
		nachnameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("nachname"));
		emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
		rolleCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("typ"));
		stempelCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("date"));
		telefonCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("telefon"));
		stadtCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("stadt"));
		adresseCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("strasse"));
		nummerCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("hausnummer"));
		
	}
	
	public void schreibeDummis() {
		Date date = new Date();
		ObservableList<Person> pList = FXCollections.observableArrayList(
				new Person (1, date,"Daman", "Kaur", "Student","Hölderlinstraße","3","Siegen","123","asd@asd.de"),
				new Person (1, date,"Ömer", "Tümen", "Student","Hölderlinstraße","3","Siegen","123","asd@asd.de"),
				new Person (1, date,"Kevin", "Nocon", "Student","Hölderlinstraße","3","Siegen","123","asd@asd.de"),
				new Person (1, date,"Martin", "Marburger", "Student","Hölderlinstraße","3","Siegen","123","asd@asd.de"));
		tableView.setItems(pList);
	}
	
}

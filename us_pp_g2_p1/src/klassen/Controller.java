package klassen;
import com.sun.xml.internal.bind.v2.schemagen.episode.Package;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class Controller extends Application {
	private Verwaltung verwaltung;
	Stage dialog;
	
	public static void main(String[] args){
		Application.launch(Controller.class, args);
	}
	
	///////////////////////////////   Tabelle Personen   ////////////////////////////////
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
			neuesFenster("/gui/personen_eingabe.fxml");
			initialisiereTabellen();
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
		Person person = tableView.getSelectionModel().getSelectedItem();
		System.out.println(person.getVorname());   
		System.out.println("Bearbeitet");
	}
	
	@FXML
	private Button loeschenButtonPerson;
	@FXML
	void loeschenGeklicktPerson(ActionEvent event) {
		System.out.println("Geloescht");
	}
	
	@FXML
	private Button buttonFilterPerson;
	@FXML
	void filterPerson(ActionEvent event) {
		System.out.println("Gefiltert");
	}
	
	@FXML
	private Button buttonAllePersonen;
	@FXML
	void allePersonen(ActionEvent event) {
		ladeAllePersonen();
		System.out.println("Alle Personen");
	}
	
	@FXML
	private ComboBox<String> comboPersonen;
	
	///////////////////////     Personen Eingabe Fenster   //////////////////////////////
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
	private TextField nummerFeld;
	
	@FXML
	private Button speichernButtonPersonen;
	@FXML
	void personSpeichern(ActionEvent event) {
		String vorname = vornameFeld.getText();
		String nachname = nachnameFeld.getText();
		String rolle = rolleFeld.getText();
		String strasse = strasseFeld.getText();
		String stadt = stadtFeld.getText();
		String plz = plzFeld.getText();
		String telefon = telefonFeld.getText();
		String email = emailFeld.getText();
		String nummer = nummerFeld.getText();
		if(vorname.isEmpty() || nachname.isEmpty()
				|| email.isEmpty() || telefon.isEmpty()
				|| rolle.isEmpty() || plz.isEmpty()
				|| stadt.isEmpty() || strasse.isEmpty() || nummer.isEmpty()){
			System.out.println(nachnameFeld.getText());
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			verwaltung.addPerson(vorname, nachname, rolle, strasse, nummer, stadt, telefon, email);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	}
	
	/////////////////////////////////     Controller    //////////////////////////////////////////////
	
	public Controller(){
		System.out.println("asd");
		verwaltung = new Verwaltung();
		//ladeAllePersonen();
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
	
	public void ladeAllePersonen() {
		try {
			verwaltung.givePerson(verwaltung.ladeAllePersonen());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void neuesFenster(String fxml) throws Exception{
        Stage dialog = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(page);
        dialog.setScene(scene);
        dialog.show();
    }
	
	public void initialisiereTabellen() {
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Option 1",
			        "Option 2",
			        "Option 3"
			    );
		comboPersonen.setItems(options);
		// Personen
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
		Date date = new Date(2322414);
		ObservableList<Person> pList = FXCollections.observableArrayList(
				new Person (1, date,"Daman", "Kaur", "Student","Hölderlinstraße","3","Siegen","123","asd@asd.de"),
				new Person (2, date,"Ömer", "Tümen", "Student","Hölderlinstraße","3","Siegen","123","asd@asd.de"),
				new Person (3, date,"Kevin", "Nocon", "Student","Hölderlinstraße","3","Siegen","123","asd@asd.de"),
				new Person (4, date,"Martin", "Marburger", "Student","Hölderlinstraße","3","Siegen","123","asd@asd.de"));
		tableView.setItems(pList);
	}
	
}

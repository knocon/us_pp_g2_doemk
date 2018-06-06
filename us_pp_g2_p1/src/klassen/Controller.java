package klassen;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller extends Application {
	private Verwaltung verwaltung;
	Stage dialog;
	int init = 0;
	
	public static void main(String[] args){
		Application.launch(Controller.class, args);
	}
	
	@FXML
	private Label toolText;
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
	private TextField filterFieldPerson;
	
	@FXML
	private Button anlegenButtonPerson;
	@FXML
	void anlegenGeklicktPerson(ActionEvent event) {
		try {
			neuesFenster("/gui/personen_eingabe.fxml", "Anlegen einer neuen Person");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button bearbeitenButtonPerson;
	@FXML
	void bearbeitenGeklicktPerson(ActionEvent event) {
		Person person = tableView.getSelectionModel().getSelectedItem();
		if(person!=null) {
			try {
				Stage st = new Stage();
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/personen_eingabe.fxml"));
		        
		        Parent sceneEingabe;
			    sceneEingabe = loader.load();

			    ControllerPersonEingabe controller = loader.<ControllerPersonEingabe>getController();
			    controller.setzePerson(person);

		        Scene scene = new Scene(sceneEingabe);
		        st.setScene(scene);
		        st.setTitle("Bearbeiten einer neuen Person");
		        st.show();
		        schreibeStatus("Person bearbeitet");
			} catch (Exception e){
				Alert abfrage = new Alert(AlertType.ERROR,"Error.", ButtonType.OK);
				e.printStackTrace();
			}
		}
		else {
			Alert abfrage = new Alert(AlertType.ERROR,"Sie müssen eine Zeile in der Tabelle auswählen.", ButtonType.OK);
			abfrage.showAndWait();
		}
		schreibeStatus("Person bearbeitet");
	}

	@FXML
	private Button loeschenButtonPerson;
	@FXML
	void loeschenGeklicktPerson(ActionEvent event) {
		Person person = tableView.getSelectionModel().getSelectedItem();
		if(person!=null) {
			Alert abfrage = new Alert(AlertType.CONFIRMATION,"Wollen Sie die Person wirklich löschen?", ButtonType.YES, ButtonType.NO);
			abfrage.showAndWait();
			if(abfrage.getResult() == ButtonType.YES) {
				verwaltung.deletePerson(person.getPersId());
				ladeAllePersonen();
				schreibeStatus("Person Gelöscht");
			}
		}
		else {
			Alert abfrage = new Alert(AlertType.ERROR,"Sie müssen eine Zeile in der Tabelle auswählen.", ButtonType.OK);
			abfrage.showAndWait();
		}
	}
	
	@FXML
	private Button buttonFilterPerson;
	@FXML
	void filterPerson(ActionEvent event) {
		String filterParam = comboPersonen.getValue();
		String filterWert = filterFieldPerson.getText();
		ObservableList<Person> pList = null;
		if(!filterWert.isEmpty()) {
			switch(filterParam) {
				case "Vorname" : pList = verwaltung.filterByParameter("vorname", filterWert, "Person"); break;
				case "Nachname" : pList = verwaltung.filterByParameter("nachname", filterWert, "Person"); break;
				case "Rolle" : pList = verwaltung.filterByParameter("typ", filterWert, "Person"); break;
				case "Stadt" : pList = verwaltung.filterByParameter("stadt", filterWert, "Person"); break;
			}
		}
		
		tableView.setItems(pList);
		System.out.println(filterFieldPerson.getText());
	}
	
	@FXML
	private Button buttonAllePersonen;
	@FXML
	void allePersonen(ActionEvent event) {
		ladeAllePersonen();
		schreibeStatus("Alle Personen werden angezeigt");
	}
	
	@FXML
	private ComboBox<String> comboPersonen;
	
	////////////////////////////////     Auftragsverwaltung   /////////////////////////////////////////
	@FXML
	private TableView<Auftrag> auftragTable;
	@FXML
	private TableColumn<Person, String> titelAuftrag;
	@FXML
	private TableColumn<Person, String> artAuftrag;
	@FXML
	private TableColumn<Person, String> dateiAuftrag;
	@FXML
	private TableColumn<Person, String> pKostenAuftrag;
	@FXML
	private TableColumn<Person, String> rKostenAuftrag;
	@FXML
	private TableColumn<Person, String> statusAuftrag;
	@FXML
	private TableColumn<Person, String> datumAuftrag;
	
	@FXML ComboBox<String> comboAuftrag;
	
	@FXML
	private Button buttonAlleAuftraege;
	@FXML
	void alleAuftraege(ActionEvent event) {
		
	}
	
	@FXML
	private Button filterAuftraege;
	@FXML
	void filterAuftrag(ActionEvent event) {
		
	}
	
	@FXML
	private Button anlegenButtonAuftrag;
	@FXML
	void anlegenGeklicktAuftrag(ActionEvent event) {
		
	   
	}
	
	@FXML
	private Button bearbeitenButtonAuftrag;
	@FXML
	void bearbeitenGeklicktAuftrag(ActionEvent event) {
		Auftrag auftrag = auftragTable.getSelectionModel().getSelectedItem();
		if(auftrag!=null) {
			try {
				Stage st = new Stage();
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/auftrag_eingabe.fxml"));

		        Parent sceneEingabe;
			    sceneEingabe = loader.load();

		        ControllerAuftragEingabe controller = loader.<ControllerAuftragEingabe>getController();
			    controller.setzeAuftrag(auftrag);

		        Scene scene = new Scene(sceneEingabe);
		        st.setScene(scene);
		        st.setTitle("Bearbeiten eines neuen Auftrags");
		        st.show();
		        schreibeStatus("Auftrag bearbeitet");
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		else {
			Alert abfrage = new Alert(AlertType.ERROR,"Sie müssen eine Zeile in der Tabelle auswählen.", ButtonType.OK);
			abfrage.showAndWait();
		}
	}
	
	@FXML
	private Button loeschenButtonAuftrag;
	@FXML
	void loeschenGeklicktAuftrag(ActionEvent event) {
		
	}
	
	@FXML
	private Button exportButtonAuftrag;
	@FXML
	void exportGeklicktAuftrag(ActionEvent event) {
		
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
	
	@FXML
	public void initialize() {
		// Personen
		ObservableList<String> optionsPers = 
			    FXCollections.observableArrayList(
			        "Vorname",
			        "Nachname",
			        "Rolle",
			        "Stadt"
			    );
		comboPersonen.setItems(optionsPers);
		comboPersonen.getSelectionModel().selectFirst();
		
		vornameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("vorname"));
		nachnameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("nachname"));
		emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
		rolleCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("typ"));
		stadtCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("stadt"));
		stempelCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("dateString"));
		telefonCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("telefon"));
		adresseCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("strasse"));
		nummerCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("hausnummer"));	
		ladeAllePersonen();
		
		// Aufträge
		ObservableList<String> optionsAuf = 
			    FXCollections.observableArrayList(
			        "Titel",
			        "Art",
			        "Status"
			    );
		comboAuftrag.setItems(optionsAuf);
		comboAuftrag.getSelectionModel().selectFirst();
		
		titelAuftrag.setCellValueFactory(
                new PropertyValueFactory<Person, String>("titel"));
		artAuftrag.setCellValueFactory(
                new PropertyValueFactory<Person, String>("art"));
		dateiAuftrag.setCellValueFactory(
                new PropertyValueFactory<Person, String>("dateiname"));
		pKostenAuftrag.setCellValueFactory(
                new PropertyValueFactory<Person, String>("pkosten"));
		rKostenAuftrag.setCellValueFactory(
                new PropertyValueFactory<Person, String>("rkosten"));
		statusAuftrag.setCellValueFactory(
                new PropertyValueFactory<Person, String>("datum"));
	}
	
	public void ladeAllePersonen() {
		ObservableList<Person> personen = verwaltung.ladeAllePersonen();
		tableView.setItems(personen);
	}
	
	public void ladeAlleAuftraege() {
		ObservableList<Auftrag> auftraege = verwaltung.ladeAlleAuftraege();
		auftragTable.setItems(auftraege);
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
	
	private void schreibeStatus(String status) {
		String nStatus = "eLab Verwaltung  :  " + status;
		toolText.setText(nStatus);
	}
	
	private void befuelleFenster(Person person) {
		System.out.println("Hi");
	}
}

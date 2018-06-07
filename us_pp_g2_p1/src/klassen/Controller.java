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
import javafx.scene.control.MenuItem;
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
	
	//////////////////////////////          Menu          ////////////////////////////////
	@FXML
	private MenuItem loginMenu;
	@FXML
	void loginGeklickt(ActionEvent event) {
		try {
			neuesFenster("/gui/login.fxml", "Login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private MenuItem registrierenMenu;
	@FXML
	void registrierenGeklickt(ActionEvent event) {
		try {
			neuesFenster("/gui/registrieren.fxml", "Registrierung");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	private TableColumn<Auftrag, String> titelAuftrag;
	@FXML
	private TableColumn<Auftrag, String> artAuftrag;
	@FXML
	private TableColumn<Auftrag, String> dateiAuftrag;
	@FXML
	private TableColumn<Auftrag, String> pKostenAuftrag;
	@FXML
	private TableColumn<Auftrag, String> rKostenAuftrag;
	@FXML
	private TableColumn<Auftrag, String> statusAuftrag;
	@FXML
	private TableColumn<Auftrag, String> datumAuftrag;
	
	@FXML ComboBox<String> comboAuftrag;
	
	@FXML
	private Button buttonAlleAuftraege;
	@FXML
	void alleAuftraege(ActionEvent event) {
		ladeAlleAuftraege();
		schreibeStatus("Alle Aufträge werden angezeigt");
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
		try {
			neuesFenster("/gui/auftrag_eingabe.fxml", "Anlegen eines neuen Auftrags");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	private Button personenButtonAuftrag;
	@FXML
	void personenGeklicktAuftrag(ActionEvent event) {
		try {
			Stage st = new Stage();
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/auftrag_personen.fxml"));

	        Parent sceneEingabe;
		    sceneEingabe = loader.load();

		    ControllerAuftragBetreuer controller = loader.<ControllerAuftragBetreuer>getController();
		    //controller.setzeAuftrag(auftrag);

	        Scene scene = new Scene(sceneEingabe);
	        st.setScene(scene);
	        st.setTitle("Bearbeiten der Betreuer");
	        st.show();
	        schreibeStatus("Betreuer bearbeitet");
		} catch (Exception e){
			e.printStackTrace();
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
	
	/////////////////////////////////       Aufträge       //////////////////////////////////////////////
	@FXML
	private TableView<Rechnung> rechnungTabelle;
	@FXML
	private TableColumn<Rechnung, String> nameRechnung;
	@FXML
	private TableColumn<Rechnung, String> datumRechnung;
	@FXML
	private TableColumn<Rechnung, String> auftraggeberRechnung;
	@FXML
	private TableColumn<Rechnung, String> kasseRechnung;
	@FXML
	private TableColumn<Rechnung, String> bezahlungRechnung;
	@FXML
	private TableColumn<Rechnung, String> kontoRechnung;
	@FXML
	private TableColumn<Rechnung, String> betragRechnung;
	@FXML
	private TableColumn<Rechnung, String> statusRechnung;
	@FXML
	private TableColumn<Rechnung, String> stempelRechnung;
	
	@FXML
	private Button anlegenButtonRechnung;
	@FXML
	void anlegenGeklicktRechnung(ActionEvent event) {
		try {
			neuesFenster("/gui/rechnung_eingabe.fxml", "Anlegen einer neuen Rechnung");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button bearbeitenButtonRechnung;
	@FXML
	void bearbeitenGeklicktRechnung(ActionEvent event) {
		
	}
	
	@FXML
	private Button loeschenButtonRechnung;
	@FXML
	void loeschenGeklicktRechnung(ActionEvent event) {
		
	}
	
	@FXML
	private Button pdfExport;
	@FXML
	void pdfExportGeklickt(ActionEvent event) {
		
	}
	
	/////////////////////////////////      Bauteile        ///////////////////////////////////////////////
	@FXML
	private ComboBox<String> comboBauteil;
	@FXML
	private ComboBox<String> comboBauteilKategorie;
	
	@FXML
	private TableView<Bauteil> bauteileTable;
	@FXML
	private TableColumn<Bauteil, String> nameBauteil;
	@FXML
	private TableColumn<Bauteil, String> preisBauteil;
	@FXML
	private TableColumn<Bauteil, String> lagerortBauteil;
	@FXML
	private TableColumn<Bauteil, String> lagerBauteil;
	@FXML
	private TableColumn<Bauteil, String> geplantBauteil;
	@FXML
	private TableColumn<Bauteil, String> bestelltBauteil;
	@FXML
	private TableColumn<Bauteil, String> linkBauteil;
	
	@FXML
	private Button inkrementierenButton;
	@FXML
	void inkrementierenBauteil(ActionEvent event){
		
	}
	
	@FXML
	private Button dekrementierenButton;
	@FXML
	void dekrementierenBauteil(ActionEvent event){
		
	}
	
	@FXML
	private Button buttonAlleBauteile;
	@FXML
	void alleBauteile(ActionEvent event){
		
	}
	
	@FXML
	private Button plusButton;
	@FXML
	void plusKategorie(ActionEvent event){
		try {
			neuesFenster("/gui/kategorien.fxml", "Anlegen einer neuen Kategorie");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button minusButton;
	@FXML
	void minusKategorie(ActionEvent event){
		
	}
	
	@FXML
	private Button buttonFilterBauteil;
	@FXML
	void filterBauteil(ActionEvent event){
		
	}
	
	@FXML
	private Button bearbeitenKategorie;
	@FXML
	void kategorieBearbeiten(ActionEvent event){
		try {
			Stage st = new Stage();
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/kategorien.fxml"));

	        Parent sceneEingabe;
		    sceneEingabe = loader.load();

		    ControllerKategorien controller = loader.<ControllerKategorien>getController();
		    //controller.setzeAuftrag(auftrag);

	        Scene scene = new Scene(sceneEingabe);
	        st.setScene(scene);
	        st.setTitle("Bearbeiten der Kategorie");
	        st.show();
	        schreibeStatus("Kategorie bearbeitet");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button bauteilAnlegenButton;
	@FXML
	void bauteilAnlegen(ActionEvent event){
		try {
			neuesFenster("/gui/bauteil_eingabe.fxml", "Anlegen eines neuen Bauteils");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button bauteilBearbeitenButton;
	@FXML
	void bauteilBearbeiten(ActionEvent event){
		try {
			Stage st = new Stage();
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/bauteil_eingabe.fxml"));

	        Parent sceneEingabe;
		    sceneEingabe = loader.load();

		    ControllerBauteilEingabe controller = loader.<ControllerBauteilEingabe>getController();
		    //controller.setzeAuftrag(auftrag);

	        Scene scene = new Scene(sceneEingabe);
	        st.setScene(scene);
	        st.setTitle("Bearbeiten des Bauteils");
	        st.show();
	        schreibeStatus("Bauteil bearbeitet");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button loeschenButtonBauteil;
	@FXML
	void bauteilLoeschen(ActionEvent event){
		
	}
	
	
	/////////////////////////////////       Controller      //////////////////////////////////////////////
	
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
                new PropertyValueFactory<Auftrag, String>("titel"));
		artAuftrag.setCellValueFactory(
                new PropertyValueFactory<Auftrag, String>("art"));
		dateiAuftrag.setCellValueFactory(
                new PropertyValueFactory<Auftrag, String>("dateiname"));
		pKostenAuftrag.setCellValueFactory(
                new PropertyValueFactory<Auftrag, String>("pkosten"));
		rKostenAuftrag.setCellValueFactory(
                new PropertyValueFactory<Auftrag, String>("rkosten"));
		statusAuftrag.setCellValueFactory(
                new PropertyValueFactory<Auftrag, String>("datum"));
		ladeAlleAuftraege();
		
		// Rechnungen
		nameRechnung.setCellValueFactory(
                new PropertyValueFactory<Rechnung, String>("rechnungsName"));
		datumRechnung.setCellValueFactory(
                new PropertyValueFactory<Rechnung, String>("rechnungsDatum"));
		auftraggeberRechnung.setCellValueFactory(
                new PropertyValueFactory<Rechnung, String>("auftraggeber"));
		kasseRechnung.setCellValueFactory(
                new PropertyValueFactory<Rechnung, String>("typ"));
		bezahlungRechnung.setCellValueFactory(
                new PropertyValueFactory<Rechnung, String>("stadt"));
		kontoRechnung.setCellValueFactory(
                new PropertyValueFactory<Rechnung, String>("dateString"));
		betragRechnung.setCellValueFactory(
                new PropertyValueFactory<Rechnung, String>("telefon"));
		statusRechnung.setCellValueFactory(
                new PropertyValueFactory<Rechnung, String>("status"));
		stempelRechnung.setCellValueFactory(
                new PropertyValueFactory<Rechnung, String>("hausnummer"));
		ladeAlleRechnungen();
		
		// Bauteile
		nameBauteil.setCellValueFactory(
                new PropertyValueFactory<Bauteil, String>("name"));
		preisBauteil.setCellValueFactory(
                new PropertyValueFactory<Bauteil, String>("epreis"));
		//lagerortBauteil.setCellValueFactory(
        //        new PropertyValueFactory<Bauteil, String>("auftraggeber"));
		lagerortBauteil.setCellValueFactory(
                new PropertyValueFactory<Bauteil, String>("bestandLager"));
		geplantBauteil.setCellValueFactory(
                new PropertyValueFactory<Bauteil, String>("bestandGeplant"));
		bestelltBauteil.setCellValueFactory(
                new PropertyValueFactory<Bauteil, String>("bestandBestellt"));
		linkBauteil.setCellValueFactory(
                new PropertyValueFactory<Bauteil, String>("link"));
		ladeAlleBauteil();
	}
	
	public void ladeAllePersonen() {
		ObservableList<Person> personen = verwaltung.ladeAllePersonen();
		tableView.setItems(personen);
	}
	
	public void ladeAlleAuftraege() {
		ObservableList<Auftrag> auftraege = verwaltung.ladeAlleAuftraege();
		auftragTable.setItems(auftraege);
	}
	
	public void ladeAlleRechnungen() {
		ObservableList<Rechnung> rechnungen = verwaltung.ladeAlleRechnungen();
		rechnungTabelle.setItems(rechnungen);
	}
	
	public void ladeAlleBauteil() {
		BauteileLogik bauteileLogik;
		//ObservableList<Bauteil> bauteile = bauteileLogik.ladeAlleBauteile();
		//bauteileTable.setItems(bauteile);
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
	//
	
	private void schreibeStatus(String status) {
		String nStatus = "eLab Verwaltung  :  " + status;
		toolText.setText(nStatus);
	}
	
}

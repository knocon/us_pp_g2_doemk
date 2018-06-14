package klassen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.itextpdf.text.DocumentException;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller extends Application {
	private Verwaltung verwaltung;
	Stage dialog;
	int init = 0;
	static ResultSet resultSet;
	static java.sql.Statement statement;
	static PreparedStatement pst = null;
	static ObservableList options = FXCollections.observableArrayList();
	static ObservableList leer = FXCollections.observableArrayList();
	/*
	 * eingeloggterAccount soll daf�r da sein, um zu wissen, welcher account gerade
	 * eingeloggt ist... notwendig f�r bauteileverwaltung
	 */
	private static int eingeloggterAccount = 2;
	private static String eingeloggterAccountName = "knocon";

	public static void main(String[] args) {
		Application.launch(Controller.class, args);
	}

	////////////////////////////// Menu ////////////////////////////////
	@FXML
	private Menu topMenu;

	@FXML
	private MenuItem loginMenu;

	@FXML
	void loginGeklickt(ActionEvent event) {
		try {
			Stage st = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/login.fxml"));

			Parent sceneEingabe;
			sceneEingabe = loader.load();

			ControllerLogin controller = loader.<ControllerLogin>getController();
			// controller.setzePerson(person);

			Scene scene = new Scene(sceneEingabe);
			st.setScene(scene);
			st.setTitle("Login");
			st.show();
		} catch (Exception e) {
			Alert abfrage = new Alert(AlertType.ERROR, "Error.", ButtonType.OK);
			abfrage.show();
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
	private MenuItem verwaltungMenu;

	@FXML
	void verwaltung(ActionEvent event) {
		if (eingeloggterAccount != 2) {
			Alert alert = new Alert(AlertType.ERROR,
					"Sie müssen als Verwalter eingeloggt sein, um die Verwaltung zu starten.", ButtonType.OK);
			alert.show();
		} else {
			try {
				neuesFenster("/gui/account_verwaltung.fxml", "Account Verwaltung");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private Label toolText;
	/////////////////////////////// Tabelle Personen
	/////////////////////////////// ////////////////////////////////
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
		if (person != null) {
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
			} catch (Exception e) {
				Alert abfrage = new Alert(AlertType.ERROR, "Error.", ButtonType.OK);
				e.printStackTrace();
			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.",
					ButtonType.OK);
			abfrage.showAndWait();
		}
		schreibeStatus("Person bearbeitet");
	}

	@FXML
	private Button loeschenButtonPerson;

	@FXML
	void loeschenGeklicktPerson(ActionEvent event) {
		Person person = tableView.getSelectionModel().getSelectedItem();
		if (person != null) {
			Alert abfrage = new Alert(AlertType.CONFIRMATION, "Wollen Sie die Person wirklich löschen?", ButtonType.YES,
					ButtonType.NO);
			abfrage.showAndWait();
			if (abfrage.getResult() == ButtonType.YES) {
				verwaltung.deletePerson(person.getPersId());
				ladeAllePersonen();
				schreibeStatus("Person Gelöscht");
			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.",
					ButtonType.OK);
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
		if (!filterWert.isEmpty()) {
			switch (filterParam) {
			case "Vorname":
				pList = verwaltung.filterByParameterPerson("vorname", filterWert, "Person");
				break;
			case "Nachname":
				pList = verwaltung.filterByParameterPerson("nachname", filterWert, "Person");
				break;
			case "Rolle":
				pList = verwaltung.filterByParameterPerson("typ", filterWert, "Person");
				break;
			case "Stadt":
				pList = verwaltung.filterByParameterPerson("stadt", filterWert, "Person");
				break;
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

	//////////////////////////////// Auftragsverwaltung
	//////////////////////////////// /////////////////////////////////////////
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

	@FXML
	ComboBox<String> comboAuftrag;
	@FXML
	TextField eingabeAuftrag;
	@FXML
	ComboBox<String> comboAuftragFilter;

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
		String filterParam = comboAuftrag.getValue();
		String filterWert = eingabeAuftrag.getText();
		ObservableList<Auftrag> aList = null;
		if (!filterWert.isEmpty()) {
			switch (filterParam) {
			case "Titel":
				aList = verwaltung.filterByParameterAuftrag("titel", filterWert, "Auftrag");
				break;
			case "Status":
				aList = verwaltung.filterByParameterAuftrag("Status", filterWert, "Auftrag");
				break;
			case "Art":
				aList = verwaltung.filterByParameterAuftrag("art", filterWert, "Auftrag");
				break;
			}
		}

		auftragTable.setItems(aList);
		System.out.println(filterFieldPerson.getText());
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
		if (auftrag != null) {
			try {
				Stage st = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/auftrag_eingabe.fxml"));

				Parent sceneEingabe;
				sceneEingabe = loader.load();
				verwaltung.deleteAuftrag(auftrag.getAufId());
				ControllerAuftragEingabe controller = loader.<ControllerAuftragEingabe>getController();
				controller.setzeAuftrag(auftrag);

				Scene scene = new Scene(sceneEingabe);
				st.setScene(scene);
				st.setTitle("Bearbeiten eines neuen Auftrags");
				st.show();
				schreibeStatus("Auftrag bearbeitet");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.",
					ButtonType.OK);
			abfrage.showAndWait();
		}
	}

	@FXML
	private Button personenButtonAuftrag;

	@FXML
	void personenGeklicktAuftrag(ActionEvent event) {
		

		try {
			neuesFenster("/gui/auftrag_pers.fxml", "Person");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button statusButtonAuf;

	@FXML
	void statusAuftrag(ActionEvent event) {
		Auftrag auftrag = auftragTable.getSelectionModel().getSelectedItem();
		try {
			Stage st = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/status_auftrag.fxml"));

			Parent sceneEingabe;
			sceneEingabe = loader.load();

			ControllerStatusAuftrag controller = loader.<ControllerStatusAuftrag>getController();
			controller.setStatus(auftrag);

			Scene scene = new Scene(sceneEingabe);
			st.setScene(scene);
			st.setTitle("Bearbeiten einer neuen Person");
			st.show();
			schreibeStatus("Person bearbeitet");

		} catch (Exception e) {
			Alert abfrage = new Alert(AlertType.ERROR, "Error.", ButtonType.OK);
			e.printStackTrace();
		}
	}

	@FXML
	private Button loeschenButtonAuftrag;

	@FXML
	void loeschenGeklicktAuftrag(ActionEvent event) {
		Auftrag auftrag = auftragTable.getSelectionModel().getSelectedItem();
		if (auftrag != null) {
			Alert abfrage = new Alert(AlertType.CONFIRMATION, "Wollen Sie diesen Auftrag wirklich löschen?",
					ButtonType.YES, ButtonType.NO);
			abfrage.showAndWait();
			if (abfrage.getResult() == ButtonType.YES) {
				verwaltung.deleteAuftrag(auftrag.getAufId());
				ladeAlleAuftraege();
				schreibeStatus("Auftrag Gelöscht");
			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.",
					ButtonType.OK);
			abfrage.showAndWait();
		}
	}

	@FXML
	private Button exportButtonAuftrag;

	@FXML
	void exportGeklicktAuftrag(ActionEvent event) {
		Auftrag auftrag = auftragTable.getSelectionModel().getSelectedItem();
		if (auftrag != null) {
			Alert abfrage = new Alert(AlertType.CONFIRMATION, "Wollen Sie diesen Auftrag wirklich exportieren?",
					ButtonType.YES, ButtonType.NO);
			abfrage.showAndWait();
			if (abfrage.getResult() == ButtonType.YES) {
				verwaltung.exportAuftrag(auftrag);
			}
		}
	}

	@FXML
	private Button datumButton;

	@FXML
	void datumAnlegenAuftrag(ActionEvent event) {
		Auftrag auftrag = auftragTable.getSelectionModel().getSelectedItem();
		verwaltung.addDatum(auftrag);
	}

	@FXML
	private Button aendernButton;

	@FXML
	void aendernStatusAuftrag(ActionEvent event) throws SQLException {
		long time = System.currentTimeMillis();
		Date date = new Date(time);

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");
		String dateString = format.format(date);
		Auftrag auftrag = auftragTable.getSelectionModel().getSelectedItem();
		String statusParam = comboAuftragFilter.getValue();
		if (auftrag != null) {
			Alert abfrage = new Alert(AlertType.CONFIRMATION, "Wollen Sie diesen Status wirklich bearbeiten?",
					ButtonType.YES, ButtonType.NO);
			abfrage.showAndWait();
			if (abfrage.getResult() == ButtonType.YES) {

				System.out.println("SatusDatum:" + dateString);
				System.out.println(auftrag.getAufId());
				int id = auftrag.getAufId();
				switch (statusParam) {
				case "Angenommen":
					verwaltung.statusAuftrag("Angenommen", auftrag.getAufId());
					verwaltung.datumEingabe(dateString, auftrag, "angenommen", id);
					;
					break;
				case "Abgeholt":
					verwaltung.statusAuftrag("Abgeholt", auftrag.getAufId());
					verwaltung.datumEingabe(dateString, auftrag, "abgeholt", id);
					;
					break;
				case "Gefertigt":
					verwaltung.statusAuftrag("Gefertigt", auftrag.getAufId());
					verwaltung.datumEingabe(dateString, auftrag, "gefertig", id);
					;
					break;
				case "Abgerechnet":
					verwaltung.statusAuftrag("Abgerechnet", auftrag.getAufId());
					verwaltung.datumEingabe(dateString, auftrag, "abgerechnet", id);
					;
					break;
				case "Kosten kalkuliert":
					verwaltung.statusAuftrag("Kosten kalkuliert", auftrag.getAufId());
					verwaltung.datumEingabe(dateString, auftrag, "kostenK", id);
					;
					break;
				case "Warten auf Material":
					verwaltung.statusAuftrag("Warten auf Material", auftrag.getAufId());
					verwaltung.datumEingabe(dateString, auftrag, "warten", id);
					;
					break;
				case "Fertigung":
					verwaltung.statusAuftrag("Fertigung", auftrag.getAufId());
					verwaltung.datumEingabe(dateString, auftrag, "fertigungU", id);
					;
					break;
				}
				ladeAlleAuftraege();
			} else {
				Alert ab = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.", ButtonType.OK);
				ab.showAndWait();
			}
		}

	}

	///////////////////////////////// Rechnungen
	///////////////////////////////// //////////////////////////////////////////////
	@FXML
	private TableView<Rechnung> rechnungTabelle;
	@FXML
	private TableColumn<Rechnung, String> nameRechnung;
	@FXML
	private TableColumn<Rechnung, String> topfName;
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
	private ComboBox<String> comboRechnungTopf;

	@FXML
	private ComboBox<String> statusCombo;
	@FXML
	private ComboBox<String> comboRechn;
	@FXML
	TextField eingabeRechnung;

	@FXML
	private Button buttonAlleRechnungen;

	@FXML
	void alleRechnungen(ActionEvent event) {
		ladeAlleRechnungen();
		schreibeStatus("Alle Rechnungen werden angezeigt");
	}

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
		Rechnung rechnung = rechnungTabelle.getSelectionModel().getSelectedItem();
		if (rechnung != null) {
			try {
				Stage st = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/rechnung_eingabe.fxml"));

				Parent sceneEingabe;
				sceneEingabe = loader.load();
				verwaltung.deleteRechnung(rechnung.getRechId());
				ControllerRechnungEingabe controller = loader.<ControllerRechnungEingabe>getController();
				controller.setzeRechnung(rechnung);

				Scene scene = new Scene(sceneEingabe);
				st.setScene(scene);
				st.setTitle("Bearbeiten einer neuen Rechnung");
				st.show();
				schreibeStatus("Rechnung bearbeitet");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.",
					ButtonType.OK);
			abfrage.showAndWait();
		}
	}

	@FXML
	private Button statusButtonFin;

	@FXML
	void statusRechnungen(ActionEvent event) {
		try {
			Stage st = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/status_rechnung.fxml"));

			Parent sceneEingabe;
			sceneEingabe = loader.load();

			ControllerStatusRechnung controller = loader.<ControllerStatusRechnung>getController();
			// controller.setzePerson(person);

			Scene scene = new Scene(sceneEingabe);
			st.setScene(scene);
			st.setTitle("Bearbeiten einer neuen Person");
			st.show();
			schreibeStatus("Person bearbeitet");
		} catch (Exception e) {
			Alert abfrage = new Alert(AlertType.ERROR, "Error.", ButtonType.OK);
			e.printStackTrace();
		}
	}

	@FXML
	private Button loeschenButtonRechnung;

	@FXML
	void loeschenGeklicktRechnung(ActionEvent event) {
		Rechnung rechnung = rechnungTabelle.getSelectionModel().getSelectedItem();
		if (rechnung != null) {
			Alert abfrage = new Alert(AlertType.CONFIRMATION, "Wollen Sie diese Rechung wirklich löschen?",
					ButtonType.YES, ButtonType.NO);
			abfrage.showAndWait();
			if (abfrage.getResult() == ButtonType.YES) {
				verwaltung.deleteRechnung(rechnung.getRechId());
				ladeAlleRechnungen();
				schreibeStatus("Rechnung gelöscht");
			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.",
					ButtonType.OK);
			abfrage.showAndWait();
		}
	}

	@FXML
	private Button statusAButton;

	@FXML
	void aendernStatus(ActionEvent event) {
		Rechnung rechnung = rechnungTabelle.getSelectionModel().getSelectedItem();
		String statusParam = statusCombo.getValue();
		if (rechnung != null) {
			Alert abfrage = new Alert(AlertType.CONFIRMATION, "Wollen Sie diesen Status wirklich bearbeiten?",
					ButtonType.YES, ButtonType.NO);
			abfrage.showAndWait();
			if (abfrage.getResult() == ButtonType.YES) {
				switch (statusParam) {
				case "Bearbeitung":
					verwaltung.statusRechnung("Bearbeitung", rechnung.getRechId());
					;
					break;
				case "Eingereicht":
					verwaltung.statusRechnung("Eingereicht", rechnung.getRechId());
					;
					break;
				case "Abgewickelt":
					verwaltung.statusRechnung("Abgewickel", rechnung.getRechId());
					;
					break;
				case "Ausstehend":
					verwaltung.statusRechnung("Ausstehend", rechnung.getRechId());
					;
					break;

				}
				ladeAlleRechnungen();
			} else {
				Alert ab = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.", ButtonType.OK);
				ab.showAndWait();
			}
		}

	}

	@FXML
	private Button minusButtonTopf;

	@FXML
	void minusTopf(ActionEvent event) {
		try {
			neuesFenster("/gui/minustopf.fxml", "Anlegen eines Topfes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button plusButtonTopf;

	@FXML
	void plusTopf(ActionEvent event) {
		try {
			neuesFenster("/gui/plustopf.fxml", "Anlegen eines Topfes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button filterRechnung;

	@FXML
	void filterRechnung(ActionEvent event) {
		String filterParam = comboRechn.getValue();
		String filterWert = eingabeRechnung.getText();
		ObservableList<Rechnung> aList = null;
		if (!filterWert.isEmpty()) {
			switch (filterParam) {
			case "Name":
				aList = verwaltung.filterByParameterRechnung("rechnungsName", filterWert, "Rechnung");
				break;
			case "Kasse":
				aList = verwaltung.filterByParameterRechnung("kassenId", filterWert, "Rechnung");
				break;
			case "Auftraggeber":
				aList = verwaltung.filterByParameterRechnung("auftraggeber", filterWert, "Rechnung");
				break;
			case "Status":
				aList = verwaltung.filterByParameterRechnung("status", filterWert, "Rechnung");
				break;
			case "Topf":
				aList = verwaltung.filterByParameterRechnung("topf", filterWert, "Rechnung");
			}
		}

		rechnungTabelle.setItems(aList);
		System.out.println(filterFieldPerson.getText());
	}

	@FXML
	private Button pdfExport;

	@FXML
	void pdfExportGeklickt(ActionEvent event) {
		Rechnung rechnung = rechnungTabelle.getSelectionModel().getSelectedItem();
		if (rechnung != null) {
			Alert abfrage = new Alert(AlertType.CONFIRMATION, "Wollen Sie die Rechnung wirklich exportieren",
					ButtonType.YES, ButtonType.NO);
			abfrage.showAndWait();
			if (abfrage.getResult() == ButtonType.YES) {
				int id = rechnung.getRechId();
				System.out.println(id);
				try {
					PDFexport export = new PDFexport();
					String pdfName = rechnung.getRechnungsName();
					export.exportPDF(id, pdfName);
					System.out.println("111");
					schreibeStatus("Rechnung exportiert");
					System.out.println("222");
				} catch (FileNotFoundException | SQLException | DocumentException e) {
					e.printStackTrace();
				}
			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Rechnung in der Tabelle auswählen.",
					ButtonType.OK);
			abfrage.showAndWait();
		}
	}

	///////////////////////////////// Bauteile
	///////////////////////////////// ///////////////////////////////////////////////
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
	private TableColumn<Bauteil, String> lagerndBauteil;
	@FXML
	private TableColumn<Bauteil, String> geplantBauteil;
	@FXML
	private TableColumn<Bauteil, String> bestelltBauteil;
	@FXML
	private TableColumn<Bauteil, String> linkBauteil;
	@FXML
	private TableColumn<Bauteil, String> kategorieBauteil;

	@FXML
	private Button inkrementierenButton;

	@FXML
	void inkrementierenBauteil(ActionEvent event) {

		if (checkBerechtigungUser() == true || checkBerechtigungAdmin() == true) {

			Bauteil b = bauteileTable.getSelectionModel().getSelectedItem();
			if (b != null) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Bestätigungsabfrage");
				alert.setHeaderText("Achtung!");
				alert.setContentText(
						"Sind Sie sicher, dass Sie das Bauteil inkrementieren möchten? Um die Inkrementierung rückgängig zu machen, müssen Sie es wieder manuell dekrementieren!");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					Verwaltung.inkrementLager(b.getTeilId());
					ladeAlleBauteile();

				} else {

				}

			} else {
				Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.",
						ButtonType.OK);
				abfrage.showAndWait();
			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Ihnen fehlen die nötigen Berechtigungen!", ButtonType.OK);
			abfrage.showAndWait();
		}

	}

	@FXML
	private Button warenkorbButton;

	@FXML
	void warenkorbAnzeigen(ActionEvent event) {
		try {
			neuesFenster("/gui/warenkorb.fxml", "Ihr Warenkorb");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button verwaltungButton;

	@FXML
	void verwaltungRechnungen(ActionEvent event) {
		try {
			if (checkBerechtigungAdmin() == true) {
				neuesFenster("/gui/verwaltung.fxml", "Admin-Tool Rechnungen");
			} else {
				Alert abfrage = new Alert(AlertType.ERROR, "Ihnen fehlen die nötigen Berechtigungen!", ButtonType.OK);
				abfrage.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button dekrementierenButton;

	@FXML
	void dekrementierenBauteil(ActionEvent event) {

		if (checkBerechtigungUser() == true || checkBerechtigungAdmin() == true) {

			Bauteil b = bauteileTable.getSelectionModel().getSelectedItem();

			if (b != null) {

				if (b.getBestandLager() == 0) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Kritischer Fehler");
					alert.setHeaderText("Bestand = 0");
					alert.setContentText("Der Bestand kann nicht weiter dekrementiert werden.");

					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Bestätigungsabfrage");
					alert.setHeaderText("Achtung!");
					alert.setContentText(
							"Sind Sie sicher, dass Sie das Bauteil dekrementieren möchten? Um die Dekrementierung rückgängig zu machen, müssen Sie es wieder manuell inkrementieren!");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						System.out.println(b.getName());
						Verwaltung.dekrementLager(b);
						ladeAlleBauteile();

					}

				}
			} else {

				Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.",
						ButtonType.OK);
				abfrage.showAndWait();

			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Ihnen fehlen die nötigen Berechtigungen!", ButtonType.OK);
			abfrage.showAndWait();
		}

	}

	@FXML
	private Button buttonAlleBauteile;

	@FXML
	void alleBauteile(ActionEvent event) {
		ladeAlleBauteile();
	}

	@FXML
	private Button plusButton;

	@FXML
	void plusKategorie(ActionEvent event) {
		try {
			if (checkBerechtigungAdmin() == true) {
				neuesFenster("/gui/kategorien.fxml", "Anlegen einer neuen Kategorie");
			} else {
				Alert abfrage = new Alert(AlertType.ERROR, "Ihnen fehlen die nötigen Berechtigungen!", ButtonType.OK);
				abfrage.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button minusButton;

	@FXML
	void minusKategorie(ActionEvent event) {

		if (checkBerechtigungAdmin() == true) {
			String k = comboBauteilKategorie.getSelectionModel().getSelectedItem();
			Verwaltung.deleteZugehoerigeKategorie(k);
			Verwaltung.deleteKategorie(k);
			ladeAlleBauteile();
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Ihnen fehlen die nötigen Berechtigungen!", ButtonType.OK);
			abfrage.showAndWait();
		}

	}

	@FXML
	private Button buttonFilterBauteil;

	@FXML
	void filterBauteil(ActionEvent event) {

		String filterParam = comboBauteilKategorie.getValue();
		ObservableList<Bauteil> pList = null;
		pList = verwaltung.filterByParameterBauteil(filterParam);

		bauteileTable.setItems(pList);

	}

	@FXML
	private Button bearbeitenKategorie;

	@FXML
	void kategorieBearbeiten(ActionEvent event) {

		if (checkBerechtigungAdmin() == true) {
			try {

				String k = comboBauteilKategorie.getSelectionModel().getSelectedItem();
				String oldk = k;

				TextInputDialog d = new TextInputDialog();
				d.setTitle("Kategorie bearbeiten");
				d.setHeaderText("Umbennenung");
				d.setContentText("Kategoriename:");

				Optional<String> result = d.showAndWait();
				if (result.isPresent()) {
					Verwaltung.renameKategorie(result.get(), k);
					Verwaltung.updateKategorienBauteile(result.get(), oldk);
					ladeAlleBauteile();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Ihnen fehlen die nötigen Berechtigungen!", ButtonType.OK);
			abfrage.showAndWait();
		}
	}

	@FXML
	private Button bauteilAnlegenButton;

	@FXML
	void bauteilAnlegen(ActionEvent event) {
		try {
			if(checkBerechtigungAdmin()==true) {
				neuesFenster("/gui/bauteil_eingabe.fxml", "Anlegen eines neuen Bauteils");
			}else {
				Alert abfrage = new Alert(AlertType.ERROR, "Ihnen fehlen die nötigen Berechtigungen!", ButtonType.OK);
				abfrage.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button bauteilBearbeitenButton;

	@FXML
	void bauteilBearbeiten(ActionEvent event) {
		if(checkBerechtigungAdmin()==true){
		Bauteil bauteil = bauteileTable.getSelectionModel().getSelectedItem();
		if (bauteil != null) {
			try {
				Stage st = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/bauteil_eingabe.fxml"));

				Parent sceneEingabe;
				sceneEingabe = loader.load();

				ControllerBauteilEingabe controller = loader.<ControllerBauteilEingabe>getController();
				controller.setzeBauteil(bauteil);

				Scene scene = new Scene(sceneEingabe);
				st.setScene(scene);
				st.setTitle("Bearbeiten eines Bauteils");
				st.show();
				schreibeStatus("Bauteil bearbeitet");
				Verwaltung.deleteBauteil(bauteil.getTeilId());
			} catch (Exception e) {
				Alert abfrage = new Alert(AlertType.ERROR, "Error.", ButtonType.OK);
				e.printStackTrace();
			}}else {Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.",
					ButtonType.OK);
			abfrage.showAndWait();
				
			}
		} else {Alert abfrage = new Alert(AlertType.ERROR, "Ihnen fehlen die nötigen Berechtigungen!", ButtonType.OK);
				abfrage.showAndWait();
			
		}
		schreibeStatus("Bauteil bearbeitet");
	}

	@FXML
	private Button loeschenButtonBauteil;

	@FXML
	void bauteilLoeschen(ActionEvent event) {
		if(checkBerechtigungAdmin()==true) {
		Bauteil b = bauteileTable.getSelectionModel().getSelectedItem();
		if (b != null) {
			
			Alert abfrage = new Alert(AlertType.CONFIRMATION, "Bauteil wirklich löschen?", ButtonType.YES,
					ButtonType.NO);
			abfrage.showAndWait();
			if (abfrage.getResult() == ButtonType.YES) {
				verwaltung.deleteBauteil(b.getTeilId());
				ladeAlleBauteile();
				schreibeStatus("Bauteil Gelöscht");
			}
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Sie müssen eine Zeile in der Tabelle auswählen.",
					ButtonType.OK);
			abfrage.showAndWait();
		}}
		else {
			Alert abfrage = new Alert(AlertType.ERROR, "Ihnen fehlen die nötigen Berechtigungen!", ButtonType.OK);
			abfrage.showAndWait();
		}

	}

	///////////////////////////////// Controller
	///////////////////////////////// //////////////////////////////////////////////

	public Controller() {
		System.out.println("asd");
		verwaltung = new Verwaltung();
	}

	@Override
	public void start(Stage hauptFenster) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/GUI.fxml"));
			System.out.println("?");
			Scene scene = new Scene(root);
			hauptFenster = new Stage();
			hauptFenster.setTitle("Bestes Verwaltungsprogramm aller Zeiten!");
			hauptFenster.setScene(scene);
			hauptFenster.setOnCloseRequest(e -> {
				e.consume();
				beenden();
			});
			// primaryStage.setMaximized(true);
			hauptFenster.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("??");

	}

	private void beenden() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Wollen sie das Programm wirklich beenden?", ButtonType.YES,
				ButtonType.NO);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			Platform.exit();
		}
	}

	@FXML
	public void initialize() {
		// Personen
		ObservableList<String> optionsPers = FXCollections.observableArrayList("Vorname", "Nachname", "Rolle", "Stadt");
		comboPersonen.setItems(optionsPers);
		comboPersonen.getSelectionModel().selectFirst();

		vornameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
		nachnameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("nachname"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		rolleCol.setCellValueFactory(new PropertyValueFactory<Person, String>("typ"));
		stadtCol.setCellValueFactory(new PropertyValueFactory<Person, String>("stadt"));
		stempelCol.setCellValueFactory(new PropertyValueFactory<Person, String>("dateString"));
		telefonCol.setCellValueFactory(new PropertyValueFactory<Person, String>("telefon"));
		adresseCol.setCellValueFactory(new PropertyValueFactory<Person, String>("strasse"));
		nummerCol.setCellValueFactory(new PropertyValueFactory<Person, String>("hausnummer"));
		ladeAllePersonen();

		// Aufträge
		ObservableList<String> optionsAuf = FXCollections.observableArrayList("Titel", "Art", "Status");
		comboAuftrag.setItems(optionsAuf);
		comboAuftrag.getSelectionModel().selectFirst();

		titelAuftrag.setCellValueFactory(new PropertyValueFactory<Auftrag, String>("titel"));
		artAuftrag.setCellValueFactory(new PropertyValueFactory<Auftrag, String>("art"));
		dateiAuftrag.setCellValueFactory(new PropertyValueFactory<Auftrag, String>("dateiname"));
		pKostenAuftrag.setCellValueFactory(new PropertyValueFactory<Auftrag, String>("pkosten"));
		rKostenAuftrag.setCellValueFactory(new PropertyValueFactory<Auftrag, String>("rkosten"));
		statusAuftrag.setCellValueFactory(new PropertyValueFactory<Auftrag, String>("status"));
		datumAuftrag.setCellValueFactory(new PropertyValueFactory<Auftrag, String>("dateString"));
		ladeAlleAuftraege();

		ObservableList<String> optionStatus = FXCollections.observableArrayList("Angenommen", "Gefertigt", "Abgeholt",
				"Abgerechnet", "Kosten kalkuliert", "Warten auf Material", "Fertigung");
		comboAuftragFilter.setItems(optionStatus);
		comboAuftragFilter.getSelectionModel().selectFirst();

		// Rechnungen
		ObservableList<String> rechnungsFilter = FXCollections.observableArrayList("Name", "Kasse", "Auftraggeber",
				"Status", "Topf");
		comboRechn.setItems(rechnungsFilter);
		comboRechn.getSelectionModel().selectFirst();

		ObservableList<String> status = FXCollections.observableArrayList("Bearbeitung", "Eingereicht", "Abgewickelt",
				"Ausstehend");
		statusCombo.setItems(status);
		statusCombo.getSelectionModel().selectFirst();

		nameRechnung.setCellValueFactory(new PropertyValueFactory<Rechnung, String>("rechnungsName"));
		topfName.setCellValueFactory(new PropertyValueFactory<Rechnung, String>("topf"));
		auftraggeberRechnung.setCellValueFactory(new PropertyValueFactory<Rechnung, String>("auftraggeber"));
		kasseRechnung.setCellValueFactory(new PropertyValueFactory<Rechnung, String>("kassenId"));
		bezahlungRechnung.setCellValueFactory(new PropertyValueFactory<Rechnung, String>("art"));
		kontoRechnung.setCellValueFactory(new PropertyValueFactory<Rechnung, String>("kontoId"));
		betragRechnung.setCellValueFactory(new PropertyValueFactory<Rechnung, String>("betrag"));
		statusRechnung.setCellValueFactory(new PropertyValueFactory<Rechnung, String>("status"));
		stempelRechnung.setCellValueFactory(new PropertyValueFactory<Rechnung, String>("dateString"));
		ladeAlleRechnungen();
		comboRechnungTopf.setItems(Verwaltung.fillComboBoxTopf());
		comboRechnungTopf.getSelectionModel().selectFirst();

		// Bauteile
		nameBauteil.setCellValueFactory(new PropertyValueFactory<Bauteil, String>("name"));

		preisBauteil.setCellValueFactory(new PropertyValueFactory<Bauteil, String>("epreis"));

		lagerortBauteil.setCellValueFactory(new PropertyValueFactory<Bauteil, String>("lagerort"));

		lagerndBauteil.setCellValueFactory(new PropertyValueFactory<Bauteil, String>("bestandLager"));

		geplantBauteil.setCellValueFactory(new PropertyValueFactory<Bauteil, String>("bestandGeplant"));

		bestelltBauteil.setCellValueFactory(new PropertyValueFactory<Bauteil, String>("bestandBestellt"));

		linkBauteil.setCellValueFactory(new PropertyValueFactory<Bauteil, String>("link"));

		kategorieBauteil.setCellValueFactory(new PropertyValueFactory<Bauteil, String>("kate"));

		ladeAlleBauteile();
		comboBauteilKategorie.setItems(Verwaltung.fillComboBoxKategorie());
		comboBauteilKategorie.getSelectionModel().selectFirst();

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

	public void ladeAlleBauteile() {
		ObservableList<Bauteil> bauteile = verwaltung.ladeAlleBauteile();
		bauteileTable.setItems(bauteile);
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

	public ComboBox<String> getComboBauteilKategorie() {
		return comboBauteilKategorie;
	}

	public void setComboBauteilKategorie(ComboBox<String> comboBauteilKategorie) {
		this.comboBauteilKategorie = comboBauteilKategorie;
	}

	public ComboBox<String> transportData() {
		ComboBox<String> output = comboBauteilKategorie;
		return output;
	}

	public static int getEingeloggterAccount() {
		return eingeloggterAccount;
	}

	public static void setEingeloggterAccount(int eingeloggterAccount) {
		Controller.eingeloggterAccount = eingeloggterAccount;
	}

	public static String getEingeloggterAccountName() {
		return eingeloggterAccountName;
	}

	public static void setEingeloggterAccountName(String eingeloggterAccountName) {
		Controller.eingeloggterAccountName = eingeloggterAccountName;
	}

	public static void login(Account acc) {
		setEingeloggterAccountName(acc.getName());
		String rolle = acc.getRolle();
		switch (rolle) {
		case "Benutzer":
			setEingeloggterAccount(1);
			break;
		case "Verwalter":
			setEingeloggterAccount(2);
			break;
		}
		Alert abfrage = new Alert(AlertType.INFORMATION, "Benutzer eingeloggt.", ButtonType.OK);
		abfrage.show();
		System.out.println((eingeloggterAccountName));
	}

	public static void logout() {
		setEingeloggterAccountName(null);
		setEingeloggterAccount(0);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/// MEINE RECHNUNGEN

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@FXML
	private Button rechnungenButton;

	@FXML
	void meineRechnungen(ActionEvent event) {
		try {
			neuesFenster("/gui/meine_rechnungen.fxml", "Ihre aktuelle Rechnungsuebersicht");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/// KATEGORIEN

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@FXML
	public void comboBauteilLaden() {
		options.setAll(leer);
		try {
			Connection con = Verwaltung.dbconnection();
			String query = "SELECT DISTINCT name FROM Kategorie";
			pst = con.prepareStatement(query);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				options.add(resultSet.getString("name"));
			}
		} catch (SQLException ex) {
			System.out.println("LOL");
		}
		comboBauteilKategorie.setItems(options);
	}

	public boolean checkBerechtigungAdmin() {

		switch (eingeloggterAccount) {
		case 0:
			return false;
		case 1:
			return false;
		case 2:
			return true;
		default:
			return false;
		}

	}

	public boolean checkBerechtigungUser() {

		switch (eingeloggterAccount) {
		case 0:
			return false;
		case 1:
			return true;
		case 2:
			return false;
		default:
			return false;
		}

	}

}

package klassen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerPersonEingabe {
	Person person;
	Controller controller;
	
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
		String telefon = telefonFeld.getText();
		String email = emailFeld.getText();
		String nummer = nummerFeld.getText();
		
		if(vorname.isEmpty() || nachname.isEmpty()
				|| email.isEmpty() || telefon.isEmpty()
				|| rolle.isEmpty() || stadt.isEmpty() 
				|| strasse.isEmpty() || nummer.isEmpty()){
			System.out.println(nachnameFeld.getText());
			Alert alert = new Alert(AlertType.ERROR,"Es fehlen noch Angaben", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			long time = System.currentTimeMillis();
			Verwaltung verwaltung = new Verwaltung();
			Person p;
			if(person!=null) {
				int id = person.getPersId();
				verwaltung.deletePerson(id);
				p = new Person(id, time ,vorname, nachname, rolle, strasse, nummer, stadt, telefon, email);
			}
			else {
				p = new Person(1, time ,vorname, nachname, rolle, strasse, nummer, stadt, telefon, email);
			}
			verwaltung.addPerson(p);
			((Node)(event.getSource())).getScene().getWindow().hide();
			System.out.println("Person angelegt");
			controller.ladeAllePersonen();
		}
	}
	
	public void initialize() {
	}
	
	public void setzeController(Controller controller) {
		this.controller = controller;
	}
	
	public void setzePerson(Person p) {
		this.person = p;
	
		vornameFeld.setText(p.getVorname());
		nachnameFeld.setText(p.getNachname());
		rolleFeld.setText(p.getTyp());
		strasseFeld.setText(p.getStrasse());
		stadtFeld.setText(p.getStadt());
		telefonFeld.setText(p.getTelefon());
		emailFeld.setText(p.getEmail());
		nummerFeld.setText(p.getHausnummer());
	}
}

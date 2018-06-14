
/*contorller
  @FXML
 
	private Button minusButtonTopf;

	@FXML
	void minusTopf(ActionEvent event) {
		if (checkBerechtigungAdmin() == true) {
			String k = comboRechnungTopf.getSelectionModel().getSelectedItem();
			Verwaltung.deleteZugehoerigeKategorie(k);
			Verwaltung.deleteKategorie(k);
			ladeAlleBauteile();
		} else {
			Alert abfrage = new Alert(AlertType.ERROR, "Ihnen fehlen die nötigen Berechtigungen!", ButtonType.OK);
			abfrage.showAndWait();
		}

	}*/

/*verwaltung 562
 public static void deleteZugehoerigenTopf(String name) {
 
String query = "DELETE FROM Topf WHERE kate ='"+name+"'";
try {
	statement.executeUpdate(query);
	System.out.println("zugehoerigkeitweg");
}catch (SQLException e) {
	e.printStackTrace();
}
}

public void deleteTopf(int id) {
String query = "DELETE FROM Topf WHERE topfId =" + id;
try {
	statement.executeUpdate(query);
	System.out.println("geloescht");
} catch (SQLException e) {
	e.printStackTrace();
}
}*/
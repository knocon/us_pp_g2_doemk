package klassen;

import java.beans.Statement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.application.Application;

public class PDFexport {
	
	public PDFexport() throws FileNotFoundException, SQLException, DocumentException{
		dbconnection();

	}
	
	static java.sql.Statement statement;
	static Connection conn = null;
	public static Connection dbconnection() {
	
			try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:database/db.db");
			statement = conn.createStatement();
			System.out.println("pdf funkt");
			return conn;
			}catch(Exception e) {
			e.printStackTrace();
			}
			return null;
	}
	
	private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

	public void exportPDF( int id, String pdfName) throws SQLException, FileNotFoundException, DocumentException {
		Font f = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD);
		ResultSet resultSet = ((java.sql.Statement) statement).executeQuery("SELECT * FROM Rechnung"); 
		Document name = new Document();
		PdfWriter.getInstance((com.itextpdf.text.Document) name, new FileOutputStream(pdfName+".pdf"));
		name.open();
		while (resultSet.next()) {
			if(resultSet.getInt("rechId")==id) {
    	
    		 Paragraph preface = new Paragraph();
    		 addEmptyLine(preface, 1);
    		 preface.add(new Paragraph("Rechnung", f));
    		  name.add(preface);
    		 
    		  Paragraph ID = new Paragraph();
    		  addEmptyLine(ID,1);
    		  ID.add(new Paragraph("Rechnungs-Id:"+"       "+resultSet.getInt("rechId")));
    		  name.add(ID);
    		  
    		 Paragraph Name = new Paragraph();
    		  addEmptyLine(Name,1);
    		  Name.add(new Paragraph("Rechnungsname:"+"   "+resultSet.getString("rechnungsName")));
    		  name.add(Name);
    		  
    		  Paragraph auftraggeber = new Paragraph();
    		 addEmptyLine(auftraggeber,1);
    		 auftraggeber.add(new Paragraph("Auftragsgeber:"+"        "+resultSet.getString("auftraggeber")));
    		 name.add(auftraggeber);
    		  
    		 Paragraph ansprechpartner = new Paragraph();
    		 addEmptyLine(ansprechpartner,1);
    		 ansprechpartner.add(new Paragraph("Ansprechpartner:"+"    "+resultSet.getString("ansprechpartner")));
    		 name.add(ansprechpartner);
    		 
    		 Paragraph kassenId = new Paragraph();
    		 addEmptyLine(kassenId,1);
    		 kassenId.add(new Paragraph("Kasse:"+"                    "+resultSet.getString("kassenId")));
    		 name.add(kassenId);
    		 
    		 
    		 Paragraph art = new Paragraph();
    		 addEmptyLine( art,1);
    		 art.add(new Paragraph("Art:"+"                         "+resultSet.getString("art")));
    		 name.add(art);
    		 
    		 Paragraph kontoId = new Paragraph();
    		 addEmptyLine( kontoId,1);
    		 kontoId.add(new Paragraph("Konto:"+"                    "+resultSet.getString("kontoId")));
    		 name.add(kontoId);
    		 
    		 Paragraph betrag = new Paragraph();
    		 addEmptyLine( betrag,1);
    		 betrag.add(new Paragraph("Betrag:"+"                   "+resultSet.getString("betrag")));
    		 name.add(betrag);
    		 
    		 Paragraph status = new Paragraph();
    		 addEmptyLine( status,1);
    		 status.add(new Paragraph("Status:"+"                   "+resultSet.getString("status")));
    		 name.add(status);
    		 
    		 /*Paragraph dateString = new Paragraph();
    		 addEmptyLine( dateString,1);
    		 dateString.add(new Paragraph("Zeitstempel:"+"           "+resultSet.getString("dateString")));
    		 name.add(dateString);*/
    		 
    		 
    		 
    		
    	 }
    }
    
    name.close();
}
}

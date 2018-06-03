package klassen;

public class PDFexport {
	/*public void exportPDF( int id) {
	 
	 Document name = new Document();
    PdfWriter.getInstance((com.itextpdf.text.Document) name, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
    ((PdfWriter) name).open();            
    //we have four columns in our table
    PdfPTable RechnungT = new PdfPTable(10);
    //create a cell object
    PdfPCell table_cell;
	 
    while (resultSet.next()) {    
   	 if(resultSet.getRowId(id).equals(id)) {
        String dept_id = resultSet.getString("Rechnung_rechId");
        table_cell=new PdfPCell(new Phrase("Rechnung_id:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_id));
        RechnungT.addCell(table_cell);
        
        String dept_rechnungsId = resultSet.getString("Rechnung_rechnungsDatum");
        table_cell=new PdfPCell(new Phrase("Rechnung_Datum:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_rechnungsId));
        RechnungT.addCell(table_cell);
        
        String dept_rechnungsName = resultSet.getString("Rechnung_rechnungsName");
        table_cell=new PdfPCell(new Phrase("Rechnung_Name:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_rechnungsName));
        RechnungT.addCell(table_cell);
        
        String dept_auftraggeber = resultSet.getString("Rechnung_auftraggeber");
        table_cell=new PdfPCell(new Phrase("Rechnung_Auftraggeber:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_auftraggeber));
        RechnungT.addCell(table_cell);
        
        String dept_ansprechpartner = resultSet.getString("Rechnung_ansprechpartner");
        table_cell=new PdfPCell(new Phrase("Rechnung_Ansprechpartner:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_ansprechpartner));
        RechnungT.addCell(table_cell);
       
        String dept_kassenId = resultSet.getString("Rechnung_kassenId");
        table_cell=new PdfPCell(new Phrase("Rechnung_Kasse:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_kassenId));
        RechnungT.addCell(table_cell);
        
        String dept_topfId = resultSet.getString("Rechnung_topfId");
        table_cell=new PdfPCell(new Phrase("Rechnung_Topf:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_topfId));
        RechnungT.addCell(table_cell);
        
        String dept_art = resultSet.getString("Rechnung_art");
        table_cell=new PdfPCell(new Phrase("Rechnung_Art:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_art));
        RechnungT.addCell(table_cell);
        
        String dept_kontoId = resultSet.getString("Rechnung_kontoId");
        table_cell=new PdfPCell(new Phrase("Rechnung_Konto:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_kontoId));
        RechnungT.addCell(table_cell);
        
        String dept_betrag = resultSet.getString("Rechnung_betrag");
        table_cell=new PdfPCell(new Phrase("Rechnung_Betrag:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_betrag));
        RechnungT.addCell(table_cell);
        
        String dept_status = resultSet.getString("Rechnung_status");
        table_cell=new PdfPCell(new Phrase("Rechnung_Status:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_status));
        RechnungT.addCell(table_cell);
        
        String dept_statusZeitstempel = resultSet.getString("Rechnung_statusZeitstempel");
        table_cell=new PdfPCell(new Phrase("Rechnung_Zeitstempel:"));
        RechnungT.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(dept_statusZeitstempel));
        RechnungT.addCell(table_cell);
        
        
   	 }
    
    }
    
    name.add(RechnungT);
    name.close();
}*/
}

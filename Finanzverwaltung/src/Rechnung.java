import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Rechnung {
	private Date rechnungsDatum;	
	private String rechnungsName;	
	private String auftraggeber;
	private String ansprechpartner;	
	private ArrayList<Kasse> kassenzugehörigkeit = new ArrayList<>();
	private ArrayList<Topf> töpfe = new ArrayList<>();	
	private String art;	
	private String konto;
	private int betrag;
	private String rechnungsStatus;	
	private Calendar rechnungsStatusZeitstempel = Calendar.getInstance();
	
	public Rechnung(Date rDatum, String rName, String rAuftragGeber, String rKontaktPerson,String artZahlung, String Konto, int Betrag) {
		super();
		this.rechnungsDatum = rDatum;
		this.rechnungsName = rName;
		this.auftraggeber = rAuftragGeber;
		this.ansprechpartner = rKontaktPerson;
		this.art = artZahlung;
		this.konto = Konto;
		this.betrag = Betrag;
		this.rechnungsStatus = "In Bearbeitung";
		this.rechnungsStatusZeitstempel.set(Calendar.HOUR_OF_DAY, 0);
	}

	public Date getRechnungsDatum() {
		return rechnungsDatum;
	}

	public void setRechnungsDatum(Date rechnungsDatum) {
		this.rechnungsDatum = rechnungsDatum;
	}

	public String getRechnungsName() {
		return rechnungsName;
	}

	public void setRechnungsName(String rechnungsName) {
		this.rechnungsName = rechnungsName;
	}

	public String getAuftraggeber() {
		return auftraggeber;
	}

	public void setAuftraggeber(String auftraggeber) {
		this.auftraggeber = auftraggeber;
	}

	public String getAnsprechpartner() {
		return ansprechpartner;
	}

	public void setAnsprechpartner(String ansprechpartner) {
		this.ansprechpartner = ansprechpartner;
	}

	public ArrayList<Kasse> getKassenzugehörigkeit() {
		return kassenzugehörigkeit;
	}

	public void setKassenzugehörigkeit(ArrayList<Kasse> kassenzugehörigkeit) {
		this.kassenzugehörigkeit = kassenzugehörigkeit;
	}

	public ArrayList<Topf> getTöpfe() {
		return töpfe;
	}

	public void setTöpfe(ArrayList<Topf> töpfe) {
		this.töpfe = töpfe;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getKonto() {
		return konto;
	}

	public void setKonto(String konto) {
		this.konto = konto;
	}

	public int getBetrag() {
		return betrag;
	}

	public void setBetrag(int betrag) {
		this.betrag = betrag;
	}

	public String getRechnungsStatus() {
		return rechnungsStatus;
	}

	public void setRechnungsStatus(String rechnungsStatus) {
		this.rechnungsStatus = rechnungsStatus;
	}

	public Calendar getRechnungsStatusZeitstempel() {
		return rechnungsStatusZeitstempel;
	}

	public void setRechnungsStatusZeitstempel(Calendar rechnungsStatusZeitstempel) {
		this.rechnungsStatusZeitstempel = rechnungsStatusZeitstempel;
	}
	
	

}

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Bill {
	private Date billDate;	//Rechnungsdatum
	private String billName;	//Rechnungname
	private String billCustomer;	//Auftraggeber
	private String contactPerson;	//Ansprechpartner
	private ArrayList<Checkout> checkouts = new ArrayList<>();	//Kassenzugeh�rigkeit
	private ArrayList<Pot> pots = new ArrayList<>();	//Topfzugeh�rigkeit
	private String kindOfPayment;	//Art der Bezahlung
	private String account;	//Konto
	private int sum;	//Betrag
	private String billState;	//Rechnungs-Status
	private Calendar billStateDate = Calendar.getInstance();	//Rechnungs-Status-Zeitstempel
	
	public Bill(Date billDate, String billName, String billCustomer, String contactPerson,String kindOfPayment, String account, int sum) {
		super();
		this.billDate = billDate;
		this.billName = billName;
		this.billCustomer = billCustomer;
		this.contactPerson = contactPerson;
		this.kindOfPayment = kindOfPayment;
		this.account = account;
		this.sum = sum;
		this.billState = "In Bearbeitung";
		this.billStateDate.set(Calendar.HOUR_OF_DAY, 0);
	}
	
	
	
	
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public String getBillCustomer() {
		return billCustomer;
	}
	public void setBillCustomer(String billCustomer) {
		this.billCustomer = billCustomer;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public ArrayList<Checkout> getCheckouts() {
		return checkouts;
	}
	public void setCheckouts(ArrayList<Checkout> checkouts) {
		this.checkouts = checkouts;
	}
	public ArrayList<Pot> getPots() {
		return pots;
	}
	public void setPots(ArrayList<Pot> pots) {
		this.pots = pots;
	}
	public String getKindOfPayment() {
		return kindOfPayment;
	}
	public void setKindOfPayment(String kindOfPayment) {
		this.kindOfPayment = kindOfPayment;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getBillState() {
		return billState;
	}
	public void setBillState(String billState) {
		this.billState = billState;
	}
	public Calendar getBillStateDate() {
		return billStateDate;
	}
	public void setBillStateDate(Calendar billStateDate) {
		this.billStateDate = billStateDate;
	}

}

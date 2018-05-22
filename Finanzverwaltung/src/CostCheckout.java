
public class CostCheckout extends Checkout{
	private String numberSequence;	//6- oder 16stellige Zahlenfolge

	CostCheckout(String aName) {
		super(aName);
	}

	public String getNumberSequence() {
		return numberSequence;
	}

	public void setNumberSequence(String numberSequence) {
		this.numberSequence = numberSequence;
	}

}

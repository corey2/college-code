//Represents a ticket that is bought in advance of an event.
package pa3A;

public class AdvanceTicket extends RegularTicket {

	private int days;

	//The price field varies depending on when the ticket is bought.
	public AdvanceTicket(int tNumber, int days) {
		super(tNumber);
		this.days=days;
		if (days>=20) {
			setPrice(15);
		}
		if (days>10 && days<20) {
			setPrice(20);
		}
	}
	
	public String toString() {
		return super.toString()+", Purchased "+days+" days in advance";
	}

}

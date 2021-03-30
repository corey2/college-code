//Represents a special type of ticket that is bought at an event for that event.
//It costs $10 more than a regular ticket.
package pa3A;

public class WalkupTicket extends RegularTicket {

	//This constructor sets the fields for the object using information from
	//the superclass. The user must set the ticket number but cannot change the
	//ticket price
	public WalkupTicket(int tNumber) {
		super(tNumber);
		super.setPrice(50);
	}	

	public String toString() {
		return super.toString()+", Walk-Up Ticket";
	}

}

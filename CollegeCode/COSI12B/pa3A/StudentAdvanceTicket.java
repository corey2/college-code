//A type of AdvanceTicket that is given to students
package pa3A;

public class StudentAdvanceTicket extends AdvanceTicket {

	//The ticket field is assigned with the RegularTicket constructor
	//The days field is assigned with the AdvanceTicket constructor
	public StudentAdvanceTicket (int tNumber, int days) {
		super(tNumber, days);
	/*Whatever the price of a regular AdvanceTicket would be at this point is 
	halved to create the student discount price*/
		setPrice(getPrice()/2);
	}

	public String toString() {
		return super.toString()+", ID required";
	}

}

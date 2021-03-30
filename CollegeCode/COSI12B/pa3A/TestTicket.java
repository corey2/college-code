package pa3A;

public class TestTicket {

	public static void main(String[] args) {
		
		//creates an array of tickets
		RegularTicket[] Tickets = new RegularTicket[16];
		//this variable is used to keep track of the total sum of the prices of
		//each ticket that has been created so far
		int sum=0;
		//Goes through each element in the array, and inserts the appropriate type
		//of ticket object.
		for (int i=0; i<=15; i++) {
			switch(i) {
				case 0:
				case 1:
				case 2:
				case 11:
				case 12:
				case 13:
				case 14:
				case 15:
					//the +1 allows the tickets to be numbered 1-16 instead of 0-15
					Tickets[i] = new RegularTicket(i+1);
					break;
				case 3:
				case 4:
					Tickets[i] = new WalkupTicket(i+1);
					break;
				case 5:
					Tickets[i] = new AdvanceTicket(i+1,30);
				    break;
				case 6:
					Tickets[i] = new AdvanceTicket(i+1,15);
				    break;
				case 7:
					Tickets[i] = new AdvanceTicket(i+1,3);
					break;
				case 8:
					Tickets[i] = new StudentAdvanceTicket(i+1,30);
					break;
				case 9:
					Tickets[i] = new StudentAdvanceTicket(i+1,15);
					break;
				case 10:
					Tickets[i] = new StudentAdvanceTicket(i+1,3);	
					break;
			}
			System.out.println(Tickets[i]);
	    	sum+=Tickets[i].getPrice();
	    	//System.out.println(sum);
		}
		System.out.println("Total profit from tickets = $"+sum);	
		
	}
	
}



package pa3A;

//Creates ticket objects that can contain a number and price and allows display of 
//itself as a string

public class RegularTicket {

	private int tNumber;
	private int price = 40;
	
	public RegularTicket(int tNumber) {
		this.tNumber = tNumber;
	}
	
	public int getTicketNumber() {
		return tNumber;
	}
	
	public void setTicketNumber(int tNumber) {
		this.tNumber = tNumber;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String toString() {
		return "Ticket number = "+tNumber+", Price = $"+price;
	}
	
}

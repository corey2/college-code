package pa1B;

public class GroceryItemOrder {

	//I made these fields public so they can easily be accessed in a GroceryList object 
	public String name;        
	public int quantity;
	public double pricePerUnit;
	
	//Takes in input values and assigns them to each field
	public GroceryItemOrder(String name,  int quantity, double pricePerUnit) {
		if (quantity<0) { 
	        throw new IllegalArgumentException(); 
	    } 
		this.name=name;
		this.quantity=quantity;
		this.pricePerUnit=pricePerUnit;
		
	}
	
	//Finds the total cost for a specific item		
	public double getCost() {
		return pricePerUnit*quantity;
	}
	

	//allows the user of the object to change the quantity to any positive number 
	public void setQuantity(int quantity) {
		if (quantity<0) { 
	        throw new IllegalArgumentException(); 
	        } 
	        this.quantity = quantity;  
	}

	//Tells the user the amount of the item that the object represents
	public String toString() {
		return quantity + " of " + name;
	    }
		
	
	
	
}

//This class represents a collection of ItemOrders as a special type of ArrayList

package pa4B;

import java.util.ArrayList;
import java.util.Collections;

public class ShoppingCart {
	//cart contains the ArrayList used by the class
	//discount controls whether the shopper gets a discount
	//first allows the program to remember if an ItemOrder has been added yet
	private ArrayList<ItemOrder> cart;
	private boolean discount;
	private boolean first;
	
	  
	public ShoppingCart() {
		cart=new ArrayList<ItemOrder>();
		first=true;
	}
	
	
	//adds ItemOrder to list/replaces existing ItemOrder with one 
	//of a different quantity
	public void add(ItemOrder order) {     
		
		//this prevents the for-loop from running before cart has any elements
		if (first) {
			cart.add(order);
			first=false;
			return;
		}
		
		//Checks the name of each ItemOrder and compares it with the name of the input 
		//ItemOrder (each item name is found indirectly). Once an identical name is found
		//, it finds the index of the corresponding ItemOrder and replaces it with the
		//new ItemOrder, and returns. 
		Item fromOrder = order.getItem(); 
		for (ItemOrder in: cart) {     	
			Item fromIn = in.getItem();          			
			if (fromOrder.getName()==fromIn.getName()) {  
				int index = cart.indexOf(in);  
				cart.set(index, order);  
				return;
			}
		}
		//If no match is found after the loop finishes iterating, it 
		//adds a new index to hold the new type of ItemOrder.
		cart.add(order);
	}
			
	public void setDiscount(boolean discount) {
		this.discount=discount;
	}
	
	//adds the total prices of each item order in the shopping cart
	public double getTotal() {
		double total=0;
		for (ItemOrder i: cart) {
			total += i.getPrice();
		}
		//calculates 10% discount
		if (discount) {
			double dAmount = total*0.1;
			total -= dAmount;
		}
		return total;
	}	

	//Uses a Collections method to sort the array since each ItemOrder implements the Comparable interface
	public void sortCart() {
		Collections.sort(cart);  
	}

	//concatenates the strings from each ItemOrder in cart 
	public String toString() {
		String s = null;
		for (ItemOrder i: cart) {
			s += i.toString();
		}
		return s;
	}
		
}
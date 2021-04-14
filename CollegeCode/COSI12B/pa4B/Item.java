//This class contains the name and price of an item and if applicable, information
//about a discount received when a certain amount of the item is bought

package pa4B;

import java.text.NumberFormat;

public class Item {
	private String name;
	private double price;
	private int quantityB = 0;
	private double priceB = 0;

	//Assigns values to the fields as long as the item is not negatively priced
	public Item(String name, double price) {
		if (price<0) {
			throw new IllegalArgumentException();
		}
		this.name=name;
		this.price=price;
	}

	//This constructor allows the user to input a certain bulk quantity 
	//and the discount price for that quantity
	public Item(String name, double price, int quantityB, double priceB) {
		if (price<0||quantityB<0||priceB<0) {
			throw new IllegalArgumentException();
		}
		this.name=name;
		this.price=price;
		this.quantityB=quantityB;
		this.priceB=priceB;
	}
	
	//Determines what the price is for different quantities of this item
	public double priceFor(int quantity) {
		//a purchase can't involve negative items
		if (quantity<0) {
			throw new IllegalArgumentException();
		}
		//when there is no bulk quantity
		if (quantityB==0) {
			return price*quantity;
		} else {
			//Finds total discount price of all "bulks" and the normal price of the
			//items that are not part of a bulk and adds them together
			double totalBulkPrice = (quantity/quantityB)*priceB;
			double remains = (quantity%quantityB)*price;
			return totalBulkPrice+remains;
			}
	}
	
	//Returns all occupied fields as a string
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		if (quantityB==0) {
			return name+", "+nf.format(price);
		} else {
			return name+", "+nf.format(price)+" ("+quantityB+" for "+nf.format(priceB)+")";
		}
	}

	public String getName() {
		return name;
	}

}

//Represents an order of items by a customer from a store

package pa4B;

public class ItemOrder implements Comparable<ItemOrder> {
	private Item item;
	private int quantity;
	

	public ItemOrder(Item item, int quantity) {
		this.item=item;
		this.quantity=quantity;
	}

	//uses a method from my item class that finds the price of an item given a quantity
	public double getPrice() {
		return item.priceFor(quantity);
	}

	public Item getItem() {
		return item;
	}

	//returns the name of the item and quantity in the order as strings
	public String toString() {
		String name = item.getName();
		return "("+name+", "+quantity+")";
	}

	//allows comparison of multiple item orders to see which one has a greater quantity
	//and the exact difference between the quantities
	public int compareTo(ItemOrder other) {
		return this.quantity - other.quantity;		

	}

}	
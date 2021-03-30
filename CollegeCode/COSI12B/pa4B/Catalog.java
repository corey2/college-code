//Represents a collection of items that are available in a store with an ArrayList

package pa4B;

import java.util.ArrayList;

public class Catalog {
	private String name;
	private ArrayList<Item> list = new ArrayList<Item>();
	
	public Catalog(String name) {
		this.name=name;
	}
	
	public void add(Item i) {
		list.add(i);
	}
	
	public int size() {
		return list.size();
	}

	public Item get(int index) {
		return list.get(index);
	}
	
	public String getName() {
		return name;
	}
	
}
package pa1B;

public class GroceryList {

	private GroceryItemOrder[] list;
	
	//Constructs the field for the object, which is an array of 10 grocery items
	public GroceryList() {
		list = new GroceryItemOrder[10];
	}	
	
	//Adds a type of grocery to the next available spot in the array
	public void add(GroceryItemOrder item) {
		for(int i=0; i<10; i++) {
			if (list[i] == null) {
				list[i]=item;
				return;	
			}
		}
		System.err.println("Your grocery list is already full");
	}	
		
	
	//Finds the cost of the items in each individual element and adds them all together
	public double getTotalCost() {
		double totalCost = 0;
		for(int i=0; i<10; i++) {
			if (list[i] != null) {
				totalCost = totalCost + list[i].getCost(); 
			} else {
				i=10;  //since all the items are put in the array in order, this saves time by preventing the loop from checking empty elements  
			}
		}	
		return totalCost;
	}

	//These accessor and mutator methods allow indirect, safe access to the state of the object
	public GroceryItemOrder[] getList() {
		return list;
	}

	public void setList(GroceryItemOrder[] list) {
		this.list = list;
	}


	

}

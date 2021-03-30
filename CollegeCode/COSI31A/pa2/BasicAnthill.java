package pa2;

public class BasicAnthill implements Anthill {

	//The anthill keeps track of the number of aardvarks, anteaters, and ants
	int aardvarknumber;
	int anteaternumber;
	int antnumber;
	String name;
	
	public BasicAnthill(String name, int antnumber) {
		this.antnumber = antnumber;
		this.aardvarknumber = 0;
		this.anteaternumber = 0;
		setName(name);
	}
	
	
	public synchronized boolean tryToEatAtWithAnimal(Animal animal) {
		if (this.antsLeft()>0) {
			//Aardvarks may not eat if there is an anteater or more than two other aardvarks
			if (animal.getName().contains("Aardvark")) {  	
				if (aardvarknumber>2 || anteaternumber>0) {
					return false;
				//Aardvarks eat here
				} else {
					System.out.println(animal.getName()+" has started eating at anthill "+this.getName()+" with priority "+animal.getPriority());		
					aardvarknumber++;
					antnumber--;
					return true;
				}
			//Anteaters can only eat alone
			} else if (animal.getName().contains("Anteater")) {
				if (aardvarknumber>0 || anteaternumber>0) {
					return false;
				} else {
					System.out.println(animal.getName()+" has started eating at anthill "+this.getName()+" with priority "+animal.getPriority());
					anteaternumber++;
					antnumber--;
					return true;
				}
			//This anthill is only available to select species
			} else {
				System.err.println("Animalnotfounderror");
				return false;
			}
		} else {
			return false;
		}
	}
		
	public synchronized void exitAnthillWithAnimal(Animal animal) {
		if (animal.getName().contains("Aardvark")) {
			aardvarknumber--;
		} else {
			anteaternumber--;
		}
		System.out.println(animal.getName()+" has eaten at anthill "+this.getName()+" with priority "+animal.getPriority());
		System.out.println(this.antsLeft());
	}

	public int antsLeft() {
		return antnumber;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getName() {
		return name;
	}

}

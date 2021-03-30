package pa2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class PriorityScheduler implements Anthill {

	ArrayList<BasicAnthill> basicAnthills;  //Keep references to BasicAnthills as private member variables inside PriorityScheduler
	private HashMap<String, Anthill> map = new HashMap<String, Anthill>();  //Keeps track of which Animals are eating at which Anthills 
	private LinkedList<Animal> queue = new LinkedList<Animal>();
	String name;
	
	public PriorityScheduler(String name, ArrayList<BasicAnthill> basicAnthills) {
		setName(name);
		this.basicAnthills = basicAnthills;
	}
	
	public synchronized boolean tryToEatAtWithAnimal(Animal animal) {
		
		if (queue.size()>0) {
			//puts animals who don't have enough priority to begin eating into a queue
			//in order by priority and makes them wait there
			for (int i=queue.size()-1; i>=0; i--) {
				if (animal.getPriority() < queue.get(i).getPriority()) {
					queue.add(i+1,animal);
					
					//System.out.println("QueueA: "+animal.getName()+" with priority "+animal.getPriority()+" has been added to the queue and now its size is "+queue.size());
					
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 	
				}
			}
		}	
			
		//loops through the BasicAnthills looking for one that is available for the animal to eat at	
		for (BasicAnthill basicAnthill: basicAnthills) {
			//System.out.println("Anthill name: "+basicAnthill.getName());
			if (basicAnthill.tryToEatAtWithAnimal(animal)) {
				map.put(animal.getName(), basicAnthill);
				return true;
			}
			//if there is nowhere to eat, the animal is put back in the queue
			int j=0;
			if (queue.size() > 0) {
				//System.out.println("Here1: "+animal.getName()+" "+animal.getPriority());
				//System.out.println("Here2: "+queue.get(j).getName()+" "+queue.get(j).getPriority());
				while (animal.getPriority() == queue.get(j).getPriority()) {
					//System.out.println("j1: "+j);
					j++;
					//System.out.println("j2: "+j);
				}
			}	
			queue.add(j,animal);
				
			//System.out.println("QueueB: "+animal.getName()+" with priority "+animal.getPriority()+" has been added to the queue and now its size is "+queue.size());
				
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		return false;
		
	}
	
	//Finds the anthill that the animal is eating at, makes it leave the anthill,
	//and notifies the other waiting animals that it has left
	public synchronized void exitAnthillWithAnimal(Animal animal) {
		String animalName = animal.getName();
		Anthill a = map.get(animalName);
		a.exitAnthillWithAnimal(animal);
		queue.remove();
		notify();
	}

	public int antsLeft() {
		return basicAnthills.size();
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}
	
	
}

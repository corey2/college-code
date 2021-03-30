package pa2;

import java.util.ArrayList;


public class Test2 {
	
	public static void main(String[] args) {
		//Initializes arrays of anthills and animals
		ArrayList<BasicAnthill> basicAnthills = new ArrayList<BasicAnthill>();
		ArrayList<Runnable> aardvarks = new ArrayList<Runnable>();
		ArrayList<Runnable> anteaters = new ArrayList<Runnable>();
		//Prepare threads
		Aardvark a;
		Anteater e;
		Runnable r;
		Thread t;
		
		//put BasicAnthills into PriorityScheduler
		for (int i=0; i<=1; i++) {
			BasicAnthill h = new BasicAnthill(i+"", 100);
			basicAnthills.add(h);
		}
		PriorityScheduler p = new PriorityScheduler("p", basicAnthills);
		for (BasicAnthill basicAnthill: p.basicAnthills) {
			System.out.println("sigh: "+basicAnthill.getName());
		}
		
		//Creates animals, sets priority, and puts them in list
		for (int i=0; i<=150; i++) {
			a = new Aardvark(i+"-Aardvark", p);
			a.setPriority(i%5);
			aardvarks.add(a);
		}
		
		for (int i=0; i<=50; i++) {
			e = new Anteater(i+"-Anteater", p);
			e.setPriority(i%5);
			anteaters.add(e);
		}
		
		//The anthills are now open for business
		for (int i=0; i<=50; i++) {
			r = aardvarks.get(i);
			t = new Thread(r);
			t.start();
		}
		
		for (int i=0; i<=50; i++) {
			r = anteaters.get(i);
			t = new Thread(r);
			t.start();
		}
		
		for (int i=51; i<=150; i++) {
			r = aardvarks.get(i);
			t = new Thread(r);
			t.start();
		}	

	
	
	}

}

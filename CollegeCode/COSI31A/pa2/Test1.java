package pa2;

import java.util.ArrayList;


public class Test1 {
	
	public static void main(String[] args) {
		//Creates the objects that are needed to use the Animals
		ArrayList<Anthill> anthills = new ArrayList<Anthill>();
		ArrayList<Runnable> aardvarks = new ArrayList<Runnable>();
		ArrayList<Runnable> anteaters = new ArrayList<Runnable>();
		Runnable r;
		Thread t;
		
		for (int i=0; i<=3; i++) {
			BasicAnthill h = new BasicAnthill(i+"", 50);
			anthills.add(h);
		}
		
		//Creates animals and puts them in list
		for (int i=0; i<=150; i++) {
			r = new Aardvark(i+"-Aardvark", anthills);
			aardvarks.add(r);
		}
		
		for (int i=0; i<=50; i++) {
			r = new Anteater(i+"-Anteater", anthills);
			anteaters.add(r);
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

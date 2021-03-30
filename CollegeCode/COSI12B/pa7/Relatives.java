package pa7;
import java.io.*;
import java.util.*;

public class Relatives {

	private static boolean hasName = false;
	public static void main(String []args) throws FileNotFoundException {	
		    //creates access to file
			System.out.println("Enter input file:");
			Scanner console = new Scanner(System.in);
			String file = console.nextLine();
			Scanner input = new Scanner(new File(file));
			
			//takes a name as input from the user
			while (true) {
				hasName = false;
				input = new Scanner(new File(file));
					
				System.out.println("Person's name ('quit' to end):");
					
				String name = console.nextLine();
				if (name.equals("quit")) {
					console.close();
					break;
				}
				
				//searches through the text file (until END) for a name that matches 
				//the one input by the user
				String next;
				while (input.hasNext()) {
					next = input.nextLine();
					//System.out.println("next: "+next);
					if (next.equals("END")) {
					    break;
					}
					if (name.equals(next)) {
					    hasName = true;
					    ArrayList<Person> people = familyTree(name, input);
					    
					    //searches People for Person whose name matches the original
					    //input and takes the Person out for use
					    Person p = new Person(name);
					    for (Person a: people){
					    	if (a.getName().equals(name)) {
					    		p = a;
					    	}
					    }
						System.out.println();
					    System.out.println("Ancestors:");
						ancestors(1, p);
						System.out.println();
						System.out.println("Descendants:");
						descendants(1, p);
						System.out.println();
					}
				}
				if (!hasName)
					System.out.println("Name does not exist");
					input.close();
			}
	}
	
	
	//creates Person objects and sets their family relationships.
    private static ArrayList<Person> familyTree(String name, Scanner input) {
		
		//skips to past the first end
		while (!input.nextLine().equals("END"));
		
		//holds the people that have been created
		ArrayList<Person> people = new ArrayList<Person>();
		
		//this loop takes into account the ordering of family roles in the text file,
		//which is family member, mother, and father, in that order
		while (input.hasNext()) {
		    String pn = input.nextLine();
		    
		    //prevents END from being made into a person
		    if (pn.equals("END")) {
		    	break;
		    }	
		    
		    //Creates family member if Person with the same name hasn't already been made
		    Person a = null;
		    for (Person x: people) {
		    	if (pn.equals(x.getName())) {
		    		a = x;
		    	}
		    }	
		    if (a==null) {
		    	a = new Person(pn);
		    	people.add(a);
		    }
		    //System.out.println("person a: " + a.getName());
		    String mn = input.nextLine();
		    String fn = input.nextLine();
		    
		    //searches through People for a Person that has the name of the family 
		    //member's mother and pairs them together
		    if (!mn.equals("unknown")) {
		    	for (Person m: people) {
		    		if (m.getName().equals(mn)) {
		    			a.setMother(m);
		    			m.addChild(a);
		    		}
		    	}
		    	
		    	//if mother hasn't been created yet, she is made and paired here
		    	if (a.getMother()==null) {
		    		Person m = new Person(mn);
		    		people.add(m);
		    		a.setMother(m);
		    		m.addChild(a);
		    	}
		    }
		    //does the same for the father
		    if (!fn.equals("unknown")) {
		    	for (Person f: people) {
		    		if (f.getName().equals(fn)) {
		    			a.setFather(f);
		    			f.addChild(a);
		    		}
		    	}
		    	if (a.getFather()==null) {
		    		Person dad = new Person(fn);
		    		people.add(dad);
		    		a.setFather(dad);
		    		dad.addChild(a);
		    	}
		    }
		}  
		return people;
    }

    //prints out the name of a person, starting with the input, finds their parents, and
    //repeats the process for each parent
    private static void ancestors(int n, Person p) {
    	printSpaces(n);	
    	System.out.println(p.getName());
    	if (p.getMother()!=null) {
    		ancestors(n+1, p.getMother());
    	}
    	if (p.getFather()!=null) {
    		ancestors(n+1, p.getFather());
    	}
    }
    
    
    //prints out the name of a person, starting with the input, finds all of their
    //children, and repeats the process for each child
    private static void descendants(int n, Person p) {	
    	printSpaces(n);	
    	System.out.println(p.getName());
    	for (Person c: p.getChildren()) {
    		descendants(n+1, c);
    	}
    }

    //prints n spaces
    public static void printSpaces(int n) {
    	if (n != 0) {
    		System.out.print(" ");
    		printSpaces(n-1);
    	}
    }
    


}
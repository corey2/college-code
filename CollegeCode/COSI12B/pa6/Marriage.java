//Don't have a properly implemented Person class
package pa6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Marriage {
	public static void main(String []args) throws FileNotFoundException {
		
		//creates access to file
		System.out.println("Choose a file of potential husbands and wives (type twice):");
		Scanner console = new Scanner(System.in);
		Scanner input = new Scanner(new File(console.next()));
		Scanner input2 = new Scanner(new File(console.next()));
		System.out.println();
		
		//objects such as People are easy to compare when they are in an ArrayList 
		ArrayList<Person> men = new ArrayList<Person>();
		ArrayList<Person> women = new ArrayList<Person>();
		
		//both provide information that each person needs
		int count = 0;
		boolean gender = true;
		
		//gathers the person represented in each line and adds them to men if they are
		//a man and women if they are a woman
		while(input.hasNextLine()) {
			String text = input.nextLine();
			Person person = processLine(gender, text, count);
			
			if (person != null) {
				if (person.getGender()==true) {
					men.add(person);
				} else {
					women.add(person);
				}
			}
			
			//When the scanner reads the word END, the program switches from creating one gender
			//to creating the other, and resets the identification count. After switching more 
			//than once though, people are going to start being overwritten.
			if (text.equalsIgnoreCase("end") && gender) {
				gender = false;
				count = -1;
			} else if (text.equalsIgnoreCase("end")) {
				gender = true;
				count = -1;
			}
		count++;
		}
		
		
		//sets up duplicates of men and women arrays that don't change when the
		//algorithm method is called; they will be used in the choice method to 
		//reference people's original preferences
		ArrayList<Person> men2 = new ArrayList<Person>();
		ArrayList<Person> women2 = new ArrayList<Person>();
		
		int count2 = 0;
		boolean gender2 = true;
				
		while(input2.hasNextLine()) {
			String text2 = input2.nextLine();
			Person person = processLine(gender2, text2, count2);
					
			if (person != null) {
				if (person.getGender()==true) {
					men2.add(person);
				} else {
					women2.add(person);
				}
			}
					
			if (text2.equalsIgnoreCase("end") && gender2) {
				gender2 = false;
				count2 = -1;
			} else if (text2.equalsIgnoreCase("end")) {
				gender2 = true;
				count2 = -1;
			}
		count2++;
		}
				
		//Creates a map that matches each person's number, whether found in their own 
		//or someone else's list of preferences, to the corresponding Person object 
		Map<Integer, Person> idMatchMen = new HashMap<Integer, Person>();
		for (Person p: men) {
			Integer number = p.getNumber();
			idMatchMen.put(number, p);
		}
				
		Map<Integer, Person> idMatchWomen = new HashMap<Integer, Person>();
		for (Person p: women) {
			Integer number = p.getNumber();
			idMatchWomen.put(number, p);
		}
				
				
		//puts the men in the algorithm as women, and women in as men, giving the
		//women an advantage at the expense of the men
		ArrayList<Person> reverseMen = new ArrayList<Person>();
		for (Person p: men) {
			reverseMen.add(p);
		}
				
		ArrayList<Person> reverseWomen = new ArrayList<Person>();
		for (Person p: women) {
			reverseWomen.add(p);
		}
		
		
		algorithm(men, women, idMatchMen);
		
		System.out.println("Man algorithm:");
		for (Person p: men) {
			if (!p.getSpouse().equals("free")) {
				System.out.println(p.getName()+" and "+p.getSpouse()+" are engaged");
			}
		}
		System.out.println();
		
		algorithm(reverseWomen, reverseMen, idMatchWomen);
		
		System.out.println("Woman algorithm:");
		for (Person p: reverseWomen) {
			if (!p.getSpouse().equals("free")) {
				System.out.println(p.getName()+" and "+p.getSpouse()+" are engaged");
			}
		}
		System.out.println();
		
		Map<String, Person> MatchNameToWoman = new HashMap<String, Person>();
		for (Person p: women) {
			String name = p.getName();
			MatchNameToWoman.put(name, p);
		}
		
		Map<String, Person> MatchNameToMan = new HashMap<String, Person>();
		for (Person p: men) {
			String name = p.getName();
			MatchNameToMan.put(name, p);
		}
		
		System.out.print("Man's ");
		choice(men, women, men2, women2, MatchNameToWoman);
		System.out.println();
		
		System.out.print("Woman's ");
		choice(women, men, women2, men2, MatchNameToMan);
		System.out.println();
	
	}
	
	//takes the information about the person from the given line including their name
	//and marriage preferences, and creates a Person object to represent them
	public static Person processLine(boolean gender, String text, int count) {
		Person person;
		Scanner line = new Scanner(text);
		String name = line.next();
		ArrayList<Integer> preferences = new ArrayList<Integer>();
		int preference = 0;
		while (line.hasNext()) {
			preference = line.nextInt();
			preferences.add(preference);
		}	
		if (name.equalsIgnoreCase("end")) {	
			person = null;
		} else {
			person = new Person(gender, name, preferences, count, "free"); 
		}
		return person; 
	}
	
	
	
	public static void algorithm(ArrayList<Person> men, ArrayList<Person> women, Map<Integer, Person> idMatch) {
		
		//"while some man m with a non-empty preference list is free"
		
		//this is a collection of men with a non-empty preference list that are free,
		//which is all of them at this point
		ArrayList<Person> condition = new ArrayList<Person>();
		for (Person c: men) {
 			condition.add(c);
 		}
		
		
		
		
		int forceQuit = 0;
		int count = 0;
		Person m = men.get(count);
		Person w = null;
		
		while (!(condition.isEmpty())) { 			
			//System.out.println("count = "+count);
			if (m.getSpouse().equals("free")) {
			
				//"w = first woman on m's list"
				for (Person person: women) {
					if (!m.getPreferences().isEmpty()) {
						if (m.getPreferences().get(0) == person.getNumber()) {
							w = person;
						}
					}			
				}
				    
				   //"if some man p is engaged to w, set p to be free"
				for (Person p: men) {
					if (p.getSpouse().equals(w.getName())) {
						p.setSpouse("free");
					}
				}
				    
				//"set m and w to be engaged to each other"
				m.setSpouse(w.getName());
				w.setSpouse(m.getName());
				
				    	
				//creates ArrayList of all men that are successors of m on the list of w
				ArrayList<Integer> successors = new ArrayList<Integer>();
				int mOnList = m.getNumber();
				for (Integer p: w.getPreferences()) {	
					successors.add(p);
				}
						
						   	
				//removes the successors from "successors" that are not actually
				//successors of m on the list of w, and m, who is not a successor 
				//of himself
				Iterator<Integer> itr = successors.iterator();
				Integer x = itr.next();
				while (x != mOnList && itr.hasNext()) {
					itr.remove();
					x = itr.next();
				}
				successors.remove(0);
				
				
				//"for (each successor q of m on w-s list)" 
				//"delete w from q-s preference list"
				//"delete q from w-s preference list"    	
	            for (Integer s: successors) {
				    Person q = idMatch.get(s);
				    int indexOfWoman = q.getPreferences().indexOf(w.getNumber());
				    q.getPreferences().remove(indexOfWoman);
				    int indexOfQ = w.getPreferences().indexOf(s);
				    w.getPreferences().remove(indexOfQ);    
	            }
	        } //big if    
	        
			//after all men have attempted marriage, we go through the list of
	        //men again
	        count++;
	        if (count == men.size()) {
	            count = 0;
	        }	
	            
	        //puts men into the while loop condition if they are free and have a non-empty preference list
	        for (Person c: men) {
	    		if (!condition.contains(c) && c.getSpouse().equals("free") && c.getPreferences().size() != 0) {
	            	condition.add(c);
	    		}
	        }
	            
	        //takes men out of the while loop condition if they are married or have a non-empty preference list    
	        Iterator<Person> cItr = condition.iterator();
	        Person c = cItr.next();
	        while (cItr.hasNext()) {
	            if (c.getPreferences().size() == 0) {            
	            	System.out.println("here");
	            	cItr.remove();
	            } else if (!c.getSpouse().equals("free")) {
	            	cItr.remove();
	            }
	            c = cItr.next();
	        }	
	            
	        //I can't remove the last element in "condition" in the previous while loop
	        //because the iterator has to be ahead of an element to move it, and being ahead
	        //of the last element would cause an "indexoutofbounds" exception. Because of
	        //all this, I remove it here.
	        if (condition.size() == 1 && !condition.get(0).getSpouse().equals("free")) {
	            condition.remove(0);
	        }
	        
	        
	        m = men.get(count);
	        forceQuit++;
	            
	        if (forceQuit == 200) {
	            for (Person remaining: men) {
	            	remaining.getPreferences().clear();
	            }
	        } 
		
		}  //while
	
	}  //method
	
	public static void choice(ArrayList<Person> men, ArrayList<Person> women, ArrayList<Person> choiceMen, ArrayList<Person> choiceWomen, Map<String, Person> matchNameToWoman) {
	
		System.out.println("Name, Choice, Partner:");
		double menCount = 0;
		double totalChoice = 0;
		
		//Checks through each man's preference list, keeping track of the number
		//of the preference that is being checked. When the preference matches the
		//ID number of the man's spouse, the current preference number is saved.
		for (Person m: choiceMen) {
			int prefCount = 0;
			int choiceNumber = 0;
			
			//m from choiceMen isn't married but his counterpart in
			//Men does have a spouse 
			String woman = null;
			for (Person thus: men) {
				if (m.getNumber() == thus.getNumber()) {
					if (thus.getSpouse().equals("free")) {
						System.out.println(m.getName()+" -- nobody");
					} else {	
						woman = (String) thus.getSpouse();
				
						Person spouseP = matchNameToWoman.get(woman); 
				
						for (int i: m.getPreferences()) {
							prefCount++;
							if (i == spouseP.getNumber()) {
								choiceNumber = prefCount;
							}
						}
						System.out.println(m.getName()+", "+choiceNumber+", "+woman);
						totalChoice += choiceNumber;
						menCount++;
					}
					
					//"totalChoice and "menCount" are used to find the average choice number that each
					//man was married to. The former is the sum of all relevant numbers and the latter
					//is the number of numbers.
					
				} //if
					
			}//for2
				
		}//for1
		System.out.println("Mean choice = "+(totalChoice/menCount)); 
		
	} //method	

}  //class


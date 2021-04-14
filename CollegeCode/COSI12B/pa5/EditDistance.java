//Doesn't work
package pa5;


import java.io.*;
import java.util.*;

public class EditDistance {

	static int count = 0;
	static String w1;
	static String w2;

	
	//The main method takes in the file that will be used as a dictionary and the words
	//that the user wants to find the edit distance of from the user. This information is
	//passed to other methods which create objects and find solutions based on it 
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Please type the name of the dictionary file you would like to use:");
		Scanner console = new Scanner(System.in);
		Scanner input = new Scanner(new File("COSI12B/pa5/"+console.next()));
		TreeMap<String, Set<String>> words = setMap(input);
		boolean done = false;
		while (!done) {
			System.out.println("Enter two words and I will find their edit distance:");
			w1 = console.next();
			w2 = console.next();
			String distance = findDistance(words);
			System.out.println(distance);
			count=0;
		    System.out.println("If you would like to use this program again type 'continue'. If not, type something else");
		    if (!(console.next().equalsIgnoreCase("continue"))) {
		    	done=true;
		    }
		}	
	}
	
	
	
	//This method creates a map matching each word in the dictionary (key) to a set
	//of its neighbor words
	public static TreeMap<String, Set<String>> setMap(Scanner input) {
		//keys="Every word"
		//values="Neighbor" 
		HashSet<String> keys = new HashSet<String>();
		HashSet<String> values = new HashSet<String>();
		TreeMap<String, Set<String>> words = new TreeMap<String, Set<String>>();
		while (input.hasNext()) {
			String word = input.next();
			keys.add(word.toLowerCase());
			values.add(word.toLowerCase());
		}
		//This outer loop creates a set of neighbor values for each key and puts
		//each key/value pair into the overall map of words and their neighbors
		for (String key: keys) {
			Set<String> neighbors = new HashSet<String>();
			//This inner loop finds the neighbor values for each key
			for (String value: values) {
				if (isNeighbor(key, value)) {
					neighbors.add(value);
				}	
			}
			words.put(key, neighbors);
		}	
		return words;
	}

	
	
	//Determines whether two Strings are neighbors.
	public static boolean isNeighbor(String key, String value) {
		int kLength = key.length();
		//If two words have a different number of letters or are the same word, we
		//know right off the bat that they aren't neighbors.
		if (!(kLength==value.length())) {
			return false;
		} else if (key.equals(value)) {
			return false;
		} else {
			int i=0;
			int same=0;
			//Counts the number of letters that each String has in common
			while (i<kLength) {
				if (key.charAt(i)==value.charAt(i)) {
					same++;
				}
				i++;
			}
			//The Strings are neighbors if they have an edit distance of 1,
			//which means that the words are different by 1 letter. 
			if (kLength-same==1) {
				return true;
			} else {
				return false;
			}
		}
	}

	
	
	
	//Finds the edit distance between two words of the user's choice.
	public static String findDistance(TreeMap<String, Set<String>> words) {
		//First two "if statements" deal with "Special Cases"
		if ( !(words.containsKey(w1)) || !(words.containsKey(w2)) ) {
			return "Word does not exist";
		} else if (w1.length() != w2.length()) {
			return "No solution";
		} else {
			//"A linked list of words to visit starting from the beginning word."
			LinkedList<String> visitees = new LinkedList<String>();
			visitees.add(w1);
			
			//Keeps track of words that have already been checked for neighbors.
			ArrayList<String> checked = new ArrayList<String>();
			
			
			//This loop searches for the second word given the first word.
			while (true) {
				
				System.out.print(w1+", ");
				checked.add(w1);
				Set<String> neighbors = words.get(w1);
				
				if (checkForWord2(visitees)) {
					break;
				}
				
				//Indicates that no path exists between the two words. 
				if (visitees.isEmpty()) {
					return "No solution";
				}
				System.out.println("Visitees: "+visitees);
				visitees.remove(w1);
				w1=visitees.get(0);
				
			
			}
			return "Edit distance = "+count;
		}	
	}

	
	
	
	public static void fillVisitees(LinkedList<String> visitees, Set<String> neighbors, ArrayList<String> checked) {
		//This loop adds all of the beginning word's neighbors (according to the map)
		//to the "visitees" list.
		for (String word: neighbors) {
			visitees.add(word);
			if (checked.contains(word)) {
				visitees.remove(word);
				
			}
		}
	}


	public static boolean checkForWord2(LinkedList<String> visitees) {
		//This loop checks the "visitees" list for the final word.
		for (String word: visitees) {
			if (word.equals(w2)) {
				System.out.println(w2);
				return true;
			}
		}
		return false;
	}







}

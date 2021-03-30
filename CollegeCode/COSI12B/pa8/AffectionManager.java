package pa8;

import java.util.*;
//This class keeps track of the current state, or progress of a game called "affection"
//where each person who attempts to kiss an assigned target while avoiding being kissed
//themselves.


public class AffectionManager {
	private AffectionNode kissFront;
	private AffectionNode schoolFront;
	
	
	//iterates through a list of participants
	public AffectionManager(List<String> names) {
		if (names == null || names.size() == 0) {
			throw new IllegalArgumentException();
		}
		Iterator<String> itr = names.iterator();
		String name = itr.next();
		while (itr.hasNext()) {
			addPersontoList(name);
			name = itr.next();
		}
	}
	
	
	//adds participants to a group of linked AffectionNode objects	
	private void addPersontoList(String name) {
		if (kissFront == null) {
			kissFront = new AffectionNode(name);
		} else {
			AffectionNode current = kissFront;
			while (current.next != null) {
				current = current.next;
			}
			current.next = new AffectionNode(name);
			
		}
	}
	
	
	//displays a list of people that are still in the game and who they are trying to
	//kiss
	public void printKissRing() {
		AffectionNode current = kissFront;
		while (current.next != null) {
			System.out.println("  "+current.name+" is so into "+current.next.name);
			current = current.next;
		}
		System.out.println("  "+current.name+" is so into "+kissFront.name);
	}

	//displays a list of people that have been eliminated from the game and who they were
	//eliminated by
	public void printSchoolyard() {
		AffectionNode current = schoolFront;
		while (current != null) {
			System.out.println("  "+current.name+" was kissed by "+current.kisser);
			current = current.next;
		}
	}

	//determines whether a given person is still in the game
	public boolean kissRingContains(String name) {
		return listContains(name, kissFront);
	}

	//determines whether a given person has already been eliminated
	public boolean schoolyardContains(String name) {
		return listContains(name, schoolFront);
	}
	
	//determines whether a given person is in the list of people still in the game,
	//or if the person has already been eliminated, depending on which method it is 
	//called by
	private static boolean listContains(String name, AffectionNode front) {
		AffectionNode current = front;
		while (current != null) {
			if (name.equalsIgnoreCase(current.name)) {
				return true;
			}
			current = current.next;
		}
		return false;	
	}
	
	
	//if only one person remains in the kiss ring, the game is over
	public boolean isGameOver() {
		if (kissFront.next == null) {
			return true;
		} else { 
			return false;
		}
	}
	
	//returns the name of the winner	
	public String winner() {
		if (kissFront.next == null) {
			return kissFront.name;
		} else {
			return null;
		}
	}
	
	//represents what happens when a person is kissed, and adjusts the progress of the
	//game accordingly
	public void kiss(String name) {
		
		//throws exceptions if the game is over or if the input person isn't in the 
		//kiss ring
		if (isGameOver()) {
			throw new IllegalStateException();
		}
		
		if (!kissRingContains(name)) {
			throw new IllegalArgumentException();
		}
		
		//keeps track of a node in the kiss ring that is needed in the algorithm
		AffectionNode krCurrent = kissFront;
		
		//keeps track of the back node of the schoolyard
		AffectionNode sCurrent = schoolFront;
		
		if (schoolFront != null) {
			while (sCurrent.next != null) {
				sCurrent = sCurrent.next;
			}
		}	
		
		//A = The schoolyard has at least 1 person in it
		//B = No one has been eliminated yet
		//case 1: The person in the front of the kiss ring is kissed
		if (kissFront.name.equalsIgnoreCase(name)) {
			while (krCurrent.next != null) {
				krCurrent = krCurrent.next;
			}
			kissFront.kisser = krCurrent.name;
				
			//A
			if (schoolFront != null) {
				sCurrent.next = kissFront;
				kissFront = kissFront.next;
				sCurrent.next.next = null;
			//B
			} else {
				schoolFront = kissFront;
				kissFront = kissFront.next;
				schoolFront.next = null;
			}
		//case 2: Any other person in the kiss ring is kissed
		} else {
			moveLinks(krCurrent, sCurrent, name);
		}
	}	


	//changes the links between each AffectionNode so that the person kissed is taken
	//from the kiss ring and added to the back of the schoolyard
	public void moveLinks(AffectionNode kr, AffectionNode s, String n) {
		while (kr.next != null) {
			if (kr.next.name.equalsIgnoreCase(n)) {
				kr.next.kisser = kr.name;
				//A
				if (schoolFront != null) {       
					s.next = kr.next;
				//B
				} else {
					schoolFront = kr.next;
				}
				kr.next = kr.next.next;			 
				//A
				if (s != null) { 				  
					s.next.next = null;
				//B
				} else {                          
					schoolFront.next = null;
					System.out.println("here");
				}
				break;
			}
			kr = kr.next;
		}
	}

} 
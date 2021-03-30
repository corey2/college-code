package pa7;
import java.util.*;

//moves disks, represented by integers, from tower A, to tower C
public class Tower {
	
	private static Stack<Integer> a = new Stack<Integer>();
	private static Stack<Integer> b = new Stack<Integer>();
	private static Stack<Integer> c = new Stack<Integer>();
	private static int moves;
	private static int size;
	
	public static void main(String[] args) {
		while (true) {
			moves = 0;
			size = 0;
			Scanner console = new Scanner(System.in);
			System.out.println("Enter number of disks (as an integer) or a number less than 1 to quit).");
			size = console.nextInt();
			System.out.println();
			if (size > 0) {
				fillTowerA(size);
				int disks = size;
				progress();
				solve(disks, a, b, c);
			} else {
				break;
			}
			
		}	
	}		
	
	//uses recursive algorithm
	public static void solve(int disks, Stack<Integer> start, Stack<Integer> temp, Stack<Integer> end) {
		if (a.isEmpty() == false || b.isEmpty() == false) {
			if (disks==1) {
				moveDisk(start, end);
				//System.out.println("here");
			} else {
				solve(disks-1, start, end, temp);
				moveDisk(start, end);	
				solve(disks-1, temp, start, end );
			}
		}	
	}			
			
			
			
			
			
			
	//sets up initial position of disks on tower A 	
	public static void fillTowerA (int size) {
		if (size > 0) {
			a.push(size);
			fillTowerA(size-1);
		}
	}

	//completes and prints the next step of the puzzle
	public static void moveDisk (Stack<Integer> start, Stack<Integer> end) {
		moves++;
		int v = start.pop();
		end.push(v);
		
		//even though the name of the stacks has changed throughout the program, they
		//are still stored in the same specific place that represents the position of
		//the tower
		
		if (start==a && end==c) {
			System.out.println("Move disc from A to C");
		} else if (start==c && end==a) {
			System.out.println("Move disc from C to A");
		} else if (start==b && end==c) {
			System.out.println("Move disc from B to C");
		} else if (start==c && end==b) {
			System.out.println("Move disc from C to B");
		} else if (start==a && end==b) {
			System.out.println("Move disc from A to B");
		} else if (start==b && end==a) {
			System.out.println("Move disc from B to A");
		}	
		progress();
	}
	
	//shows the program's progress so far
	public static void progress() {
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println("number of moves: "+moves);
		System.out.println();
	}


}
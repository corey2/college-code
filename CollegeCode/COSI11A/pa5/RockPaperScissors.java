package pa5;

//This program plays Rock Paper Scissors with the user with two different strategies
//The first strategy picks the attack choice that would have beaten the user's previous attack choice
//The second strategy usually picks the attack choice that would have beaten the program's last choice;
//The "usually" part, along with the program's first attack choice (in either strategy), depends on values
//gathered with the random class

import java.util.*;

public class RockPaperScissors {
	public static void main(String[] args) {
		Random rand = new Random(); // creates objects
		Scanner console = new Scanner(System.in);
		System.out.println("Lets play rock, paper scissors :)");
		System.out.println("Would you like to play against strategy 1 or strategy 2?");
		System.out.println("Type your desired number here:");
		int choice = console.nextInt();
		do { // user chooses the strategy that they play against
			if (choice == 1) {
				strategy1(rand, console);
			}
			if (choice == 2) {
				strategy2(rand, console);
			}
		} while (choice != 1 && choice != 2);
	}

	public static void strategy1(Random rand, Scanner console) { // the method takes in scanner and random objects as
																	// parameters so I don't have to redeclare them
		int done = 0; // this variable changes the program when it reaches 1 and ends the program when
						// it reaches 2
		String win = "I win."; // these strings are used later to display the result
		String lose = "You win.";
		String tie = "We tied.";
		int last = 0; // keeps track of the opponents choice
		int inattack = -1;// these values are arbitrary since they are changed later anyway
		int myattack = -1;
		while (done < 2) {
			if (done == 0) { // activates the first round of the game
				System.out.println(
						"Choose your weapon!!! (Type \"1\" for Rock, \"2\" for Paper, or anything other number for Scissors):");
				inattack = console.nextInt();
				int r = rand.nextInt(3); // the program chooses its first attack completely randomly
				if (r == 0) {
					myattack = 1;
				} else if (r == 1) {
					myattack = 2;
				} else {
					myattack = 3;
				}
				done++; // lets the computer know that it should activate the following statements
						// during the next iteration of the loop
			} else {
				System.out.println("Lets play again.");
				System.out.println("1=Rock; 2=Paper; Other number=Scissors;");
				inattack = console.nextInt();
				if (last == 1) { // chooses whichever attack would have beaten the user's previous attack
					myattack = 2;
				} else if (last == 2) {
					myattack = 3;
				} else {
					myattack = 1;
				}
			}
			System.out.println("Rock, paper, scissors, says shoot!!!");
			if (myattack == 1 && inattack == 2) { // these if statements follow a specific format...
				System.out.println("My ROCK VS. Your PAPER"); // this prints what both the user and the program chose
				System.out.println(lose); // this displays the victor
				last = 2; // allows the program to determine what the attack it chooses next time if the
							// game is played again
			} else if (myattack == 2 && inattack == 1) {
				System.out.println("My PAPER VS. Your ROCK");
				System.out.println(win);
				last = 1;
			} else if (myattack == 2 && inattack == 3) {
				System.out.println("My PAPER VS. Your SCISSORS");
				System.out.println(lose);
				last = 3;
			} else if (myattack == 3 && inattack == 2) {
				System.out.println("My SCISSORS VS. Your PAPER");
				System.out.println(win);
				last = 2;
			} else if (myattack == 1 && inattack == 3) {
				System.out.println("My ROCK VS. Your SCISSORS");
				System.out.println(win);
				last = 3;
			} else if (myattack == 3 && inattack == 1) {
				System.out.println("My SCISSORS VS. Your ROCK");
				System.out.println(lose);
				last = 1;
			} else if (myattack == 1 && inattack == 1) {
				System.out.println("My ROCK VS. Your ROCK");
				System.out.println(tie);
				last = 1;
			} else if (myattack == 2 && inattack == 2) {
				System.out.println("My PAPER VS. Your PAPER");
				System.out.println(tie);
				last = 2;
			} else {
				System.out.println("My SCISSORS VS. Your SCISSORS");
				System.out.println(tie);
				last = 3;
			}

			System.out.println("If you would like to stop playing, type 1"); // ends the program when the user wants to
																				// stop playing
			System.out.println("If not, type some other number:");
			if (console.nextInt() == 1) {
				done += 5; // ends the loop
			}
		}
	}

	public static void strategy2(Random rand, Scanner console) { // this method is similar to the first one except in a
																	// few key parts...
		int done = 0;
		String win = "I win.";
		String lose = "You win.";
		String tie = "We tied.";
		int last = 0; // this now keeps track of the program's attack choices rather than the user's
		int inattack = -1;
		int myattack = -1;
		while (done < 2) {
			if (done == 0) { // first round is exactly the same
				System.out.println(
						"Choose your weapon!!! (Type \"1\" for Rock, \"2\" for Paper, or any other number for Scissors):");
				inattack = console.nextInt();
				int r = rand.nextInt(3);
				if (r == 0) {
					myattack = 1;
				} else if (r == 1) {
					myattack = 2;
				} else {
					myattack = 3;
				}
				done++;
			} else {
				System.out.println("Lets play again.");
				System.out.println("1=Rock; 2=Paper; Some other random combination of number keys=Scissors;");
				inattack = console.nextInt();
				int prob = rand.nextInt(9); // the program has a certain chance of picking a certain attack choice based
											// on the choice it picked in the previous round...
				if (last == 1 && prob <= 6) { // there is a 7 out of 10 chance it will pick the choice that would have
												// beaten it's previous choice
					myattack = 2;
				} else if (last == 1 && prob == 7 || prob == 8) { // there is a 2 out of 10 chance it will pick the
																	// choice that would have lost to it's previous
																	// choice
					myattack = 3;
				} else if (last == 1 && prob == 9) { // there is a 1 out of 10 chance it will pick the same choice as it
														// did last time
					myattack = 1;
				} else if (last == 2 && prob <= 6) {
					myattack = 3;
				} else if (last == 2 && prob == 7 || prob == 8) {
					myattack = 1;
				} else if (last == 2 && prob == 9) {
					myattack = 2;
				} else if (last == 3 && prob <= 6) {
					myattack = 1;
				} else if (last == 3 && prob == 7 || prob == 8) {
					myattack = 2;
				} else {
					myattack = 3;
				}
			}
			System.out.println("Rock, paper, scissors, says shoot!!!");
			if (myattack == 1 && inattack == 2) {
				System.out.println("My ROCK VS. Your PAPER");
				System.out.println(lose);
				last = 1;
			} else if (myattack == 2 && inattack == 1) {
				System.out.println("My PAPER VS. Your ROCK");
				System.out.println(win);
				last = 2;
			} else if (myattack == 2 && inattack == 3) {
				System.out.println("My PAPER VS. Your SCISSORS");
				System.out.println(lose);
				last = 2;
			} else if (myattack == 3 && inattack == 2) {
				System.out.println("My SCISSORS VS. Your PAPER");
				System.out.println(win);
				last = 3;
			} else if (myattack == 1 && inattack == 3) {
				System.out.println("My ROCK VS. Your SCISSORS");
				System.out.println(win);
				last = 1;
			} else if (myattack == 3 && inattack == 1) {
				System.out.println("My SCISSORS VS. Your ROCK");
				System.out.println(lose);
				last = 3;
			} else if (myattack == 1 && inattack == 1) {
				System.out.println("My ROCK VS. Your ROCK");
				System.out.println(tie);
				last = 1;
			} else if (myattack == 2 && inattack == 2) {
				System.out.println("My PAPER VS. Your PAPER");
				System.out.println(tie);
				last = 2;
			} else {
				System.out.println("My SCISSORS VS. Your SCISSORS");
				System.out.println(tie);
				last = 3;
			}
			System.out.println("If you would like to stop playing, type 1");
			System.out.println("If not, type something else:");
			if (console.nextInt() == 1) {
				done += 5;
			}
		}
	}
}

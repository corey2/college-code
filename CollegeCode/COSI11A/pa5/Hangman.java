package pa5;

//This program has the user play hangman with it.
//The user inputs a word, and the number of letters it has,
//and the computer uses the random class to guess each letter 
//in the word without directly using the input word

import java.util.*;

public class Hangman {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Lets play hangman :)");
		System.out.println("Type a word and I'll try to guess it:");
		String input = console.next(); // takes in input word
		String word = input.toLowerCase();
		System.out.println("How many letters does your word have?");
		int length = console.nextInt();
		hangman(word, length); // passes on word and length as parameters
	}

	public static void hangman(String word, int length) {
		Random rand = new Random();
		int i = 0; // keeps track of number of guesses
		String guess = ""; // this while loop structure here initializes the string that is keeps track of
							// the program's progress
		int k = 0;
		while (k <= word.length() - 1) { // the test makes sure that "String guess" has the same number of characters as
											// the input word
			guess = guess + '_'; // the underscores are added as a placeholder for letters in the word that have
									// not been guessed yet
			k++;
		}
		String already = "$"; // creates the string that keeps track of letters that have already been guessed
								// (the value here is a placeholder)
		String letter = "*"; // creates the string that the program uses as its guess for a letter (the value
								// here is a placeholder)
		while (!word.equals(guess)) { // the test ends the game when the word has been completely guessed
			do {
				int preletter1 = rand.nextInt(26) + 97; // determines what letter it will guess,
				char preletter2 = (char) preletter1; // and the letter is turned into String form
				letter = preletter2 + "";
			} while (already.contains(letter)); // makes sure the same letter is not guessed twice
			int j = 0; // keeps track of the index in the input word that the program is checking
			System.out.println("Letter guessed = " + letter);
			while (j <= word.length() - 1) { // this test ends the loop when every index has been checked
				if (word.charAt(j) == letter.charAt(0)) { // if the guess is correct,
					guess = guess.substring(0, j) + letter + "" + guess.substring((j + 1), length); // the string that
																									// keeps track of
																									// progress is
																									// modified to
																									// reflect this
				}
				j++;
			}
			System.out.println(guess); // lets the user know what the progress is so far
			already += letter; // the letter that has just been used is added to the list of letters that won't
								// be guessed again
			i++;
		}
		System.out.println("I got the word in " + i + " guesses.");
	}
}

package pa4;

//This program takes a word or sentence and reverses
//the order of the characters.

import java.util.Scanner;

public class ReverseString {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in); // constructs scanner
		System.out.println("Type a word or sentence:"); // prompt
		String sentence = console.nextLine(); // takes input from console and stores it in the variable "sentence"
		reverse(sentence); // calls reverse method and inputs sentence into it
	}

	public static void reverse(String input) { // input = sentence
		int length = input.length(); // finds the length of the sentence and stores it in variable that can easily be put in loop
		for (int i = length - 1; i >= 0; i--) { // 1 is subtracted from the length because doing so gets the index of the last character in the sentence
			System.out.print(input.charAt(i)); // prints each character in reverse order
		}
	}
}

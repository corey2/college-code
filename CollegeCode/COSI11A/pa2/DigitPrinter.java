package pa2;

//This program takes a four digit number and displays each
//digit from left to right on a new line
import java.util.*;
public class DigitPrinter {
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		System.out.println("Enter a four digit number."); 
		String s = console.nextLine();
		char zero = s.charAt(0); //assigns a variable to the first digit
		System.out.println(zero); //displays first digit
		char one = s.charAt(1); //assigns a variable to the second digit
		System.out.println(one); //displays second digit
		char two = s.charAt(2); //assigns a variable to the third digit
		System.out.println(two); //displays third digit
		char three = s.charAt(3); //assigns a variable to the fourth digit
		System.out.println(three); //displays fourth digit
	}
}
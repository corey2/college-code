package pa2;

//converts a binary number into a decimal number
import java.util.*;

public class BinaryToDecimal {
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		System.out.println("Enter a four-bit binary number.");
		String binary = console.next(); //requests input of binary number as a string
		char zero = binary.charAt(0); //isolates first digit of the binary input
		int zerov = (int) zero; //turns this digit into an integer
		int a = zerov - 48;  //converts digit to appropriate integer
		int deczero = a * 8; //multiplies digit by increased power of 2
		char one = binary.charAt(1);  //does all of the above for the second digit
		int onev = (int) one;
		int b = onev - 48;
		int decone = b * 4;
		char two = binary.charAt(2);  //does all of the above for the third digit
		int twov = (int) two;
		int c = twov - 48;
		int dectwo = c * 2;
		char three = binary.charAt(3);  //does all of the above for the final digit
		int threev = (int) three;
		int d = threev - 48;
		int decthree = d * 1;
		int wholedecimal = deczero + decone + dectwo + decthree;  //adds each decimal together to recieve final answer
		System.out.println("That number is " + wholedecimal + " in decimal form.");  //outputs final answer
	}
}

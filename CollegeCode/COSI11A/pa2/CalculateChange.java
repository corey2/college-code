package pa2;

//This program determines the type and number of coins you would get
//after paying a dollar for something from a vending machine  
import java.util.*;
public class CalculateChange {
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		System.out.println("Enter price of item");
		System.out.println("(from 25 cents to a dollar, in 5-cent increments)");
		int price = console.nextInt(); //allows imput of price of item
		int change = 100-price; //declares how many cents the change is worth
		System.out.println("You gave me a dollar and bought a snack that is " + price + " cents");
		System.out.println("The change is:");
		int quarter = change/25; //finds the number of quarters
		System.out.println(quarter + " quarters");
		int changetwo = change%25; //finds the value of the remaining change
		int dime = changetwo/10; //finds the number of dimes that fit in the value of the remaining change
		System.out.println(dime + " dimes"); 
		int changethree = changetwo%10; //determines if there is five cents the dime didn't cover
		int nickel = changethree/5;  //covers these five cents with a nickel
		System.out.println(nickel + " nickels");
	}
}
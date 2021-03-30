package pa2;

//converts Fahrenheit to Celsius
import java.util.*;
public class FahrenheitToCelsius {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in); //creates scaner object
		System.out.println("Enter a temperature in degrees Fahrenheit.");
		int degreef = console.nextInt(); //allows input of Farenheit temperature
		double degreec = 5*(degreef-32)/9; //finds degrees celcius
		System.out.println(degreef + " degrees Fahrenheit is " + degreec + " degrees Celcius."); //displays degrees in Fahrenheit and Celcius
	}
}
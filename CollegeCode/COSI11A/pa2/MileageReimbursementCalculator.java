package pa2;

//This program finds the amount of reimbursement for a person who
//drives a certain number of miles at $.35 per mile 
import java.util.*;
public class MileageReimbursementCalculator {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in); 
		System.out.println("MILEAGE REIMBURSEMENT CALCULATOR");
		System.out.println("Enter beginning odometer reading: ");
		double startodometer = console.nextDouble(); //allows input of starting odometer reading
		System.out.println("Enter ending odometer reading: "); 
		double endodometer = console.nextDouble(); //allows input of ending odometer reading
		double totalmiles = endodometer - startodometer; //finds total number of miles 
		double reimbursement = 0.35 * totalmiles; //finds reimbursement
		System.out.print("You traveled " + totalmiles + " miles.");
		System.out.println("at $.35 per mile,");
		System.out.println("Your reimbursement is $ " + reimbursement);
	}
}

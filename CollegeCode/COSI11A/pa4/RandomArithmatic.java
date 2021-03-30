package pa4;

//This program takes a number and repeatedly halves it
//when it is even and multiplies it by 3 and adds 1 when
//it is odd until the value is 1. The program shows the 
//result of each step and in the end, shows the total 
//number of steps.

import java.util.*;

public class RandomArithmatic {
public static void main(String[]args) {  //the main method basically just takes the initial value and prepares it for use
  Scanner console = new Scanner (System.in);
  System.out.println("Enter a whole number:");
  int number = console.nextInt();
  System.out.println("Initial value is: "+number);
  calculations(number); //inserts number into calculations method
}
public static void calculations(int onezeronot) {
  if (onezeronot<=0) { 
      System.out.println("Error"); //if number is less than or equal to 0, you get an error
  } else if (onezeronot>1) {
      evenodd(onezeronot); //sends original input into another method if it is greater than 1
  } else {
      System.out.println("Final value: 1");
      System.out.println("Number of operations: 1"); //this is the result if the input was exactly 1 
      }
  }    
public static void evenodd(int value) { //this method actually does the bulk of the calculations
    int done=1000; //done is an arbitrarily high value so it doesn't interfere with the loop until it needs to 
    for (int i=1; i!=done+1; i++) { //the loop is what makes the calculations repeat until I need them to stop
      if (value%2==0) { //if input value is even
          value/=2; //it is divided by 2
          System.out.println("Next value is: "+value); //prints the step
          if (value==1) { //this if statement ends the program when the value is 1
              System.out.println("Final value is: 1"); 
              done=i; //"done" is the value that causes the continuation test to fail, which ends the program; 1 is added to "done" to account for the final concatenation   
              System.out.println("Number of operations is: "+i); //i represents the total number of steps     
          }      
      } else { //if the input value is odd
          value=value*3+1; //it is multiplied by 3 and increased by 1
          System.out.println("Next value is: "+value); //prints the step
      }
    }
}
}  

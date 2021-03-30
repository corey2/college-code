package pa5;

//This program asks the user to think of a number from 0 to 1000 and
//finds this number without having the user directly inputing the number
//by asking if the "guessed" number is too high or too low, and then adjusting
//its "guess" accordingly (based on averages and changing max and min values).

import java.util.*;

public class GuessNumber {
public static void main(String[]args) {
  Scanner console = new Scanner(System.in);
  System.out.println("Think of a number between 1 and 1000 and I will try to guess it.");
  System.out.println("Type any number to initiate the program when you have thought of your number:");
  int start = console.nextInt(); //the variable start is never actually used but I need console.nextInt(); to equal something
  game(console); 
}

public static void game(Scanner console) {
  int min=0;
  int max=1000;
  int done=1; //changes as a result of the sentinel value
  int gnumber=0; //keeps track of the number of guesses
  int guess=500; //this value allows the program to halve the number of possibilities right from the start
  while (done!=0) {
    System.out.println(guess);
    System.out.println("If that number is correct, type 0"); //0 is the sentinel value
    System.out.println("If that number is too high, type any positive number");
    System.out.println("If that number is too low, type any negative number");
    int input=console.nextInt();
    if (input==0) { 
      done=0;
    } else if (input>0) { //when the number is too high,
      max=guess; //we know the number can't be any higher,
      guess=((max+min)/2); //so we find the average of the max and min values that the thought-of number could be, in order to narrow down the most possibilities
    } else { //when the number is not correct or too high (a.k.a. too low by process of elimination)
      min=guess; //...lower
      guess=((max+min)/2); //...
      if (guess==999) {
        guess=1000;
      }  
    }
    gnumber++;   
  }    
  System.out.println("Your number is "+guess);
  System.out.println(" and it only took me "+(gnumber+1)+" guesses to figure that out."); //i put "(gnumber + 1)" because when I end the loop with the sentinel value, i don't get a chance to concatenate gnumber the last time  
}  
}  

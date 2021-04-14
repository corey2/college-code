package pa2;
import java.util.*;

public class Birthday {
	  public static void main(String[]args) {
		    Scanner console = new Scanner(System.in);
	 	    System.out.println("What month, day, and year were you born?");
		    int month = console.nextInt();
		    int day = console.nextInt();
		    int year = console.nextInt();		    
		    Date date = new Date(year, month, day);
		    Date today = new Date();
		    //System.out.println(today.toString());
		    display(date);
		    int wait=getWait(date);
	      if (wait==0) {
	     	    System.out.println("Happy Birthday! You are now age " + (today.getYear()-date.getYear()) +".");
	      } else {
	    	    System.out.println("It will be your birthday in "+wait+" days." );
	      }
	      int daysOfLife=getDaysOfLife(date);
	      System.out.println("Your are "+daysOfLife+" days old.");
	      console.close();
	  } 
	
	  public static void display(Date date) {  //displays all of the information that can be retrieved from the object with a single method call
		    System.out.println("You were born on "+date.toString()+", which was a "+date.getDayOfWeek());
		    if (date.isLeapYear() == true) {
		        System.out.println(date.getYear()+" was a leap year.");
		    }
	  }	
		
	  public static int getWait(Date date) {
		    Date today = new Date();  //the nextDay method tends to mess up the Date objects representing today, so its easier just to create new ones after you use them
		    int wait=0; 
		    if (date.getMonth()==today.getMonth() && date.getDay()==today.getDay()) {  //This means its your birthday
			     return wait;
		    }
	      if (date.getMonth()==today.getMonth() && date.getDay()>today.getDay()) {   //I can do this because within the same month, the difference between today and your birthday are in the consistent unit of days.
			      wait=date.getDay()-today.getDay(); 
		    } else {
			      while(!(date.getMonth()==today.getMonth() && date.getDay()==today.getDay())) {  //changes the day until it is your next birthday and keeps track of how long this takes
			          today.nextDay();
			          wait++;
			      }
		    }   		 
	      return wait;	 
    }	    

	public static int getDaysOfLife(Date date) {  //counts every day, starting from when you were born going all the way up to today
		Date today = new Date();
		int daysOfLife=0;  
		while(!(date.equals(today))) {
		  date.nextDay();  
		  daysOfLife++;
      }
		return daysOfLife; 
		}
}	  
		  

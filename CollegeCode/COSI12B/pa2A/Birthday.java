package pa2A;
import java.util.*;

/*
public class Birthday {
	  public static void main(String[]args) {
	 	    Scanner console = new Scanner(System.in);
        System.out.println("What month, day, and year were you born?");
		    int month = console.nextInt();
		    int day = console.nextInt();
		    int year = console.nextInt();
		    TeacherDate date = new TeacherDate(year, month, day);
		    TeacherDate today = new TeacherDate();
		    display(date);
	      int wait=getWait(date);
	      if (wait==0) {
	     	    System.out.println("Happy Birthday! You are now age " + (today.getYear()-date.getYear()) +".");
	      } else {
	    	    System.out.println("It will be your birthday in "+wait+" days." );
	      }
	      int daysOfLife=getDaysOfLife(date);
	      System.out.println("Your are "+daysOfLife+" days old.");
	  } 
	
	  public static void display(TeacherDate date) {
		    System.out.println("You were born on "+date.toString()+", which was a "+date.getDayOfWeek());
		    if (date.isLeapYear() == true) {
		        System.out.println(date.getYear()+" was a leap year.");
		    }
	  }	
		
	  public static int getWait(TeacherDate date) {
		    TeacherDate today = new TeacherDate();
		    int wait=0; 
		    if (date.getMonth()==today.getMonth() && date.getDay()==today.getDay()) {  //This means its your birthday
			     return wait;
		    }
	      if (date.getMonth()==today.getMonth() && date.getDay()>today.getDay()) {   //I can do this because within the same month, the difference between today and your birthday are in the consistent unit of measurement
			      wait=date.getDay()-today.getDay(); 
		    } else {
			      while(!(date.getMonth()==today.getMonth() && date.getDay()==today.getDay())) {
			          today.nextDay();
			          wait++;
			      }
		    }   		 
	      return wait;	 
    }	    

	public static int getDaysOfLife(TeacherDate date) {
		TeacherDate today = new TeacherDate();
		int daysOfLife=0;  
		while(!(date.equals(today))) {
		  date.nextDay();  
		  daysOfLife++;
      }
		return daysOfLife; 
		}
}	  
*/	  

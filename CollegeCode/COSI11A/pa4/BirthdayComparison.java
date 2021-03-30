package pa4;

//This program determines how long it is until two
//people's birthdays and reveals which birthday is sooner.

import java.util.Scanner;

public class BirthdayComparison {
public static void main(String[]args) {
  Scanner console = new Scanner (System.in); 
  System.out.println("Enter the number of the current month:"); //1. these next few lines get the current date
  int nmonth = console.nextInt();
  System.out.println("Enter the day of the month:");
  int nday = console.nextInt();
  int tdate = monthLength(nmonth, nday); //2. finds the current date in days and returns the value back to the main method
  System.out.println("Enter the number of the month of first person's birthday:"); //3. these lines get the first person's birthday
  int bmonthone = console.nextInt();
  System.out.println("Enter day of first person's birthday:");
  int bdayone = console.nextInt();
  int tbdayone = monthLength(bmonthone, bdayone); //13. finds the date of the first person's birthday in days
  int waitone = calculations(tdate, tbdayone); //14. finds how long the first person's birthday is after the current date
  System.out.println("Enter the number of the month of the second person's birthday:"); //23. Finally, on to the second persons birthday info
  int bmonthtwo = console.nextInt();
  System.out.println("Enter day of second person's birthay:");
  int bdaytwo = console.nextInt(); 
  int tbdaytwo = monthLength(bmonthtwo, bdaytwo); //24. Finds second person's birthday in total days into the year
  int waittwo = calculations(tdate, tbdaytwo); //25. Finds days until second person's birthday
  comparison(waitone, waittwo); //26. The general idea is pretty self-explanatory

}

public static int monthLength(int m, int days) { //4. this method turns months into days
  if (m==1) { //6. if the input was january
      int mtd=0; //7. the days until this month are 0
      return mtd+days;
  } else if (m==2) { //8. if the input was february
      int mtd=31; //9. the days until this month are 31
      return mtd+days;
  } else if (m==3) {
      int mtd=59;
      return mtd+days;
  } else if (m==4) {
      int mtd=90;
      return mtd+days;
  } else if (m==5) {
      int mtd=120;
      return mtd+days;
  } else if (m==6) {
      int mtd=151;
      return mtd+days;    
  } else if (m==7) {
      int mtd=181;
      return mtd+days;    
  } else if (m==8) {
      int mtd= 212;
      return mtd+days;  
  } else if (m==9) {
      int mtd=243;
      return mtd+days;
  } else if (m==10) {
      int mtd=273;
      return mtd+days;
  } else if (m==11) {
      int mtd=304;
      return mtd+days;
  } else if (m==12) {
      int mtd=335;
      return mtd+days;
  } else {           
      System.out.println("You should have entered a number from 1 to 12."); //8. in case the user doesn't print out a month
      return 131313;
  }
                    //11. the day of the year is represented by the days up to month plus the days in the month
                    //12. for example: september 4 = 243+4 = 247
}                                         

public static int calculations(int date, int bday) { //15. takes the current date and each person's birthday (in seperate executions of this method) in days from the main method
  if (date<bday) {  //16. if the person's birthday is later in the year...
      int wait=bday-date; //17. the difference between the date and the birthday gives the number of days until the birthday
      System.out.println("There are "+wait+" days until your birthday."); //18. prints the number off days until the birthday
      return wait; //19. returns the wait until the person's birthday from the current date back to the main method    
  } else if (date>bday) { //20. if the person's birthday was earlier in the year...
      int wait=(366-date)+bday; //21. the days left in the year (366-date) plus the number of days from Jan 1 to the persons birthday (bday) equals the days until the person's birthday
      System.out.println("There are "+wait+" days until your birthday.");
      return wait;     
  } else {
      int wait=0;
      System.out.println("HAPPY BIRTHDAY!!!"); //22. if it is not before or after the person's birthday, it must be their birthday
      return wait; 
  }
  
}    
  
public static void comparison(int fbdayone, int fbdaytwo) { //27. takes the days until the first person's birthday and days until the second person's birthday and stores them in the final birthday variables
  if (fbdayone<fbdaytwo) {
      System.out.println("The first person's birthday is sooner than the second person's birthday.");
  } else if (fbdayone>fbdaytwo) {
      System.out.println("The second person's birthday is sooner than the first person's birthday.");
  } else {
      System.out.println("Both people have the same birthday! What a coincidence.");
  }
}
}
       

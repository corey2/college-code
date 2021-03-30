package pa2B;
import java.util.*;

public class Date {
	private int day;
	private int month;
	private int year;
	final int daysSinceEpoch = 15386; //TeacherDate.getDaysSinceEpoch()
	
	public Date(int year, int month, int day) {
		this.year=year;
	  this.month=month;
	  this.day=day;
	}
	
	public Date() {
		day = 1;     //this method starts counting nextDays at the beginning of the year of your birthday, on Jan. 1
		month = 1;
		year = (int)(1970 + (daysSinceEpoch/365.25));            //this equation finds the years since 1970 and adds this answer to 1970 to get the current year
		int yearDiff = (int) (daysSinceEpoch/365.25);           //the difference between this year and 1970 as a positive number
		int daysTillYear =(int)(yearDiff*365.25);               //the number of days from 1970 until the beginning of this year is found with this equation
		int totaldays = (int) (daysSinceEpoch-daysTillYear);
		while (daysTillYear<daysSinceEpoch) {  //changes daysTillYear until it meets today, with the date adjusting as it approaches today 
			nextDay();
		  daysTillYear++;
		}
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getDayOfWeek() {
	  String dayOfWeek = "Monday";
	  int tempYear=this.year;     //I decided to hold the fields in temporary variables so that the calculation of the day of the week loop could end at the right time
	  int tempMonth=this.month;
	  int tempDay=this.day;  
		this.year = 1753;
		this.month = 1;
		this.month = 1;
    while (!(this.year==tempYear && this.month==tempMonth && this.day==tempDay)) {  //if only there was a way to compare THIS object to another one using the equal method...
			nextDay();                      //As the day changes from Jan 1 1753 to today, this chain of if statements keep track of the day of the week
			if (dayOfWeek == "Monday") {
				dayOfWeek = "Tuesday";			
			} else if (dayOfWeek == "Tuesday") {
				dayOfWeek = "Wednesday";
			} else if (dayOfWeek == "Wednesday") {
				dayOfWeek = "Thursday";
			} else if (dayOfWeek == "Thursday") {
				dayOfWeek = "Friday";
			} else if (dayOfWeek == "Friday") {
				dayOfWeek = "Saturday";
			} else if (dayOfWeek == "Saturday") {
				dayOfWeek = "Sunday";
			} else {
				dayOfWeek = "Monday";
			}
		}
		return dayOfWeek;      
	}
	
	
	public boolean isLeapYear() {
		if (!(year%100 == 0 && year%400 != 0)) { //if it is not true that the year is divisible by 100 and not by 400, then it can possibly be a leap year
			if (year%4 == 0) {  //every 4th year
				return true;
			}
		}
    return false;
  }
	
	public void nextDay() { 
		switch (month) {  //handles 30 day months, 31 day months, February, and December seperately
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
      if(day<=30) {
        day++;
      } else {
        day=1;
        month++;
      }
		  break;
		case 4:
		case 6:
		case 9:
		case 11:
      if(day<30) {
        day++;
      } else {
        day=1;
        month++;
      }  
      break;
    case 2:
    	if (day<28) {
        day++;
      } else if (day==28) {  //what happens after Feb. 28 varies with leap year
        	if (!(year%100 == 0 && year%400 != 0)) { 
			      if (year%4 == 0) { 
				      day++;
				    }
		      }    
		    	day=1;
			    month++;    
    	} else {  //for the unique case of the current date being exactly Feb. 29
        day=1;
        month++;
      }
      break;
    default: //12
      if (day<=30) {
        day++;
      } else {
        day=1;
        month=1;
        year++;
    	}
    	break;
    } 
	}

	
	
	public String toString() {
		return year+"/"+month+"/"+day;
	}
	
	
	
	public boolean equals(Date o) {  //Sorry for changing the parameter but it makes much more sense this way
		if (o.getDay() == day && o.getMonth() == month && o.getYear() == year) {
			return true;
		} else {
      return false;
	  }
	}

		
	
}

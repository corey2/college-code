package pa1A;

import java.awt.*;

public class Line {
  private Point p1;
  private Point p2;
  
  //constructors  
  public Line(Point p1, Point p2) {
    	this.p1=p1;
        this.p2=p2;
    }
  
  
 //allows safe access to fields
  public Point getp1() {
    return p1;
  }
  
  public Point getp2() {
    return p2;
  }
  
  //Finds the slope of the line when it is not undefined 
  public double getSlope() {
    if (p1.x==p2.x) {
        System.err.println("The denominator is zero, so the slope is undefined.");
    }  
    return (p2.y-p1.y)/(p2.x-p1.x);
  }
  
  
  
  public boolean isCollinear(Point p3) {
    double slopeA = getSlope();
    double slopeB = (p3.y-p1.y)/ (p3.x-p1.x);  //finds the slope of the line connecting point 1 and input point 3
    round(slopeA, 4);  
    round(slopeB, 4);
    if (slopeA==slopeB) {  //if lines connecting 1-2 and 1-3 have equal slope, it is implied that 2-3 is the same as well
        return true;
    } else { 
        return false;
    }
  }
  
  
  // Rounds the given value to x digits after the decimal
  public static double round(double value, int x) {
    double pow10 = Math.pow(10, x);
    return Math.round(value * pow10) / pow10;
  }  


  
 
}    
    
    